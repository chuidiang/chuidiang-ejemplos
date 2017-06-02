package com.chuidiang.examples.boothazelcast.server

import com.chuidiang.examples.boothazelcast.client.Client
import com.chuidiang.examples.boothazelcast.model.Data
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IAtomicReference
import com.hazelcast.core.IMap
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

import java.util.stream.IntStream

/**
 * Created by chuidiang on 2/06/17.
 */
@Component
class Server implements Runnable {
    static Logger log = LoggerFactory.getLogger(Server.class)
    public static final String I_AM_SERVER = "I'm server"
    public static final String THE_MODEL = "The Model"
    private IMap<Integer, Data> model = null

    Server(){
        log.info("Starting Server")

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance()
        IAtomicReference<Boolean> iAmserver = hazelcastInstance.getAtomicReference I_AM_SERVER
        if (iAmserver.compareAndSet(null,true)){
            model = hazelcastInstance.getMap(THE_MODEL)
            new Thread(this).start()
        } else {
            hazelcastInstance.shutdown()
            new Client()
        }

    }

    @Override
    void run() {
        Random random = new Random()
        IntStream ints = random.ints(0,10)
        ints.each {int value ->
            Data data = new Data(id:value, name:"Name"+value, randomValue:Math.random())
            model.put(value, data )
            log.info("""$value -> $data""")
            Thread.sleep(1000)
        }
    }
}
