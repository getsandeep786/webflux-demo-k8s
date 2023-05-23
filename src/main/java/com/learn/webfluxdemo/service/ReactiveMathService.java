package com.learn.webfluxdemo.service;

import com.learn.webfluxdemo.dto.MultiplyRequestDto;
import com.learn.webfluxdemo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input) {
        return Mono.fromSupplier(() -> {
// The main thread is stuck because the client subscription happens on the main thread.
// If you want it to run asynchronously, you need to the subscription to happen on a thread other than main
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                    System.out.println("New Testing");
                    return input * input;
                })
//                .delayElement(Duration.ofSeconds(10))
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input) {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                //.doOnNext(i -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> System.out.println("reactive-math-service processing : " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDto> dtoMono) {
        return dtoMono
                .delayElement(Duration.ofSeconds(10))
                .map(dto -> dto.getFirst() * dto.getSecond())
                .map(Response::new);
    }

}
