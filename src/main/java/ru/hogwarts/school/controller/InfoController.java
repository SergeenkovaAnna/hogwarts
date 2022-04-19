package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("/getPort")
public class InfoController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/port")
    public int getPort() {
        return port;
    }

    @GetMapping("/step_4")
    public int stepFour() {
        return Stream.iterate(1, a -> a+1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum);
    }

}
