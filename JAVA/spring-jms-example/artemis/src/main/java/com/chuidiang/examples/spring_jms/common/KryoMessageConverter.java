package com.chuidiang.examples.spring_jms.common;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import lombok.extern.slf4j.Slf4j;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.ByteArrayOutputStream;

/**
 * @author fjabellan
 * @date 29/11/2020
 */
@Slf4j
public class KryoMessageConverter implements MessageConverter {
    private Kryo kyroInput;
    private Kryo kyroOutput;

    public KryoMessageConverter(){
        kyroInput = new Kryo();
        kyroInput.setRegistrationRequired(false);
        kyroInput.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
        kyroOutput = new Kryo();
        kyroOutput.setRegistrationRequired(false);
        kyroOutput.setInstantiatorStrategy(new DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));

    }

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        synchronized (kyroOutput) {
            kyroOutput.writeClassAndObject(output, object);
            output.flush();
        }
        BytesMessage bytesMessage = session.createBytesMessage();
        bytesMessage.writeBytes(bos.toByteArray());
        return bytesMessage;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        if (!(message instanceof BytesMessage)){
            log.error("No es ByteMessage");
            return null;
        }
        BytesMessage bytesMessage = (BytesMessage)message;
        byte[] bytes = new byte[(int)bytesMessage.getBodyLength()];
        bytesMessage.readBytes(bytes);
        Input input = new Input(bytes);
        Object object=null;
        synchronized (kyroInput) {
            object = kyroInput.readClassAndObject(input);
        }
        return object;
    }
}
