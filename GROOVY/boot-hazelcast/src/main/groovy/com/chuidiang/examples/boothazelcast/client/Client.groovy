package com.chuidiang.examples.boothazelcast.client

import com.chuidiang.examples.boothazelcast.model.Data
import com.chuidiang.examples.boothazelcast.server.Server
import com.hazelcast.client.HazelcastClient
import com.hazelcast.core.EntryEvent
import com.hazelcast.core.EntryListener
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IMap
import com.hazelcast.core.MapEvent
import com.hazelcast.map.listener.MapListener
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by chuidiang on 2/06/17.
 */
class Client {
    private static Logger log = LoggerFactory.getLogger(Client.class)
    private IMap<Integer, Data> model = null

    Client(){
        log.info("Starting client")
        HazelcastInstance hzInstance = HazelcastClient.newHazelcastClient()
        model = hzInstance.getMap(Server.THE_MODEL)

        model.addEntryListener(new EntryListener() {
            @Override
            void entryAdded(EntryEvent event) {

            }

            @Override
            void entryEvicted(EntryEvent event) {

            }

            @Override
            void entryRemoved(EntryEvent event) {

            }

            @Override
            void entryUpdated(EntryEvent event) {
                log.info("""updated $event.key -> $event.value""")
            }

            @Override
            void mapCleared(MapEvent event) {

            }

            @Override
            void mapEvicted(MapEvent event) {

            }
        } as MapListener, true)
    }
}
