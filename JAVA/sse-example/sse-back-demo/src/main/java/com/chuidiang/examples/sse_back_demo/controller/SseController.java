package com.chuidiang.examples.sse_back_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.Reader;

/**
 * @author fjabellan 17/10/2024
 * Crea y devuelve un SseEmitter por cada petición desde el front http://localhost:8080/sse
 * Registra el SseEmitter en EventProducer para ser informado cada vez que se produzca un evento.
 * Desregistra SseEmitter de EventoProducer cuando se cierra la conexión.
 */
@RestController
public class SseController {
    @Autowired
    EventProducer eventProducer;

    @GetMapping("/sse")
    public SseEmitter streamSse(Reader reader) {
        // Timeout indefinido 0L
        SseEmitter sseEmitter = new SseEmitter(0L);

        // Desregistrar de EventProducer cuando se cierre la conexion
        sseEmitter.onCompletion(() -> eventProducer.remove(sseEmitter));

        // Registrar en EventProducer
        eventProducer.add(sseEmitter);

        return sseEmitter;
    }
}