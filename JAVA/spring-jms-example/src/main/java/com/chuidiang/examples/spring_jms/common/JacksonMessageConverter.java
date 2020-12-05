package com.chuidiang.examples.spring_jms.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author fjabellan
 * @date 28/11/2020
 */
@Slf4j
public class JacksonMessageConverter implements MessageConverter {
    private MappingJackson2MessageConverter jackson;
    public JacksonMessageConverter(){
        jackson = new MappingJackson2MessageConverter();
        ObjectMapper objectMapper=new ObjectMapper();

        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,true);
        objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE,true);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson.setObjectMapper(objectMapper);
        jackson.setTypeIdPropertyName(String.class.getName());
    }

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        Message message= jackson.toMessage(object,session);
        log.info("El mensaje "+message);
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        log.info("from message");
        return jackson.fromMessage(message);
    }
}
