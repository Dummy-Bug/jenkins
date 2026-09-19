package com.lab.jenkins;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final Calculator calculator;
    private final String serviceName;
    private final String version;

    public CalculatorController(Calculator calculator,
                                @Value("${spring.application.name}") String serviceName,
                                @Value("${app.version}") String version) {
        this.calculator = calculator;
        this.serviceName = serviceName;
        this.version = version;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
                "status", "UP",
                "service", serviceName,
                "version", version);
    }

    @GetMapping("/api/add")
    public Map<String, Integer> add(@RequestParam int a, @RequestParam int b) {
        return Map.of(
                "a", a,
                "b", b,
                "result", calculator.add(a, b));
    }
}
