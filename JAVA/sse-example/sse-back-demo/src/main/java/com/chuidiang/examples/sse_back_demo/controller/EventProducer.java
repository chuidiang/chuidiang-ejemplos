package com.chuidiang.examples.sse_back_demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author fjabellan 17/10/2024
 * Produce eventos cada segundo y los comunica a los SseEmitter
 */
@Component
public class EventProducer {

    /** SseEmitters a los que avisar de eventos */
    private List<SseEmitter> emitters = new ArrayList<>();

    /** Contador de eventos enviados */
    private int counter = 0;

    /** Pool de hilos */
    private ExecutorService executorService = Executors.newCachedThreadPool();

    /** Cada segundo, un evento y se notifica a los SseEmitters */
    @Scheduled(fixedRate = 1000)
    public void newEvent(){
        System.out.println("Emitiendo "+ counter++);
        emitters.forEach(emitter -> {
            executorService.submit(()-> {
                try {
                    emitter.send(new Data("Mensaje", counter));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        });
    }

    /** Añade un nuevo SseEmitter a la lista.
     * Se llama a este método cuando un nuevo cliente se conecta */
    public void add(SseEmitter sseEmitter) {
        emitters.add(sseEmitter);
        System.out.println("Añadido SseEmitter. Hay "+emitters.size());
    }

    /** Elimina un SseEmitter de la lista.
     * Se llama a este método cuando un cliente se desconecta */
    public void remove(SseEmitter sseEmitter){
        emitters.remove(sseEmitter);
        System.out.println("Borrado SseEmitter. Quedan "+emitters.size());
    }

}
