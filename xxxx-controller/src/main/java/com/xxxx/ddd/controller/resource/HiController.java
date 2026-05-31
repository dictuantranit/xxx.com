package com.xxxx.ddd.controller.resource;

import com.xxxx.ddd.application.service.event.EventAppService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hello")
public class HiController {

    @Autowired
    private EventAppService eventAppService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hi")
    @RateLimiter(name="backendA", fallbackMethod = "fallbackHello")
    public String hello(){
        return eventAppService.sayHi("Hi");
    }

    public  String fallbackHello(Throwable throwable){
        return "Too many request";
    }

    @GetMapping("/hi/v1")
    @RateLimiter(name="backendB", fallbackMethod = "fallbackHello")
    public String sayHi(){
        return eventAppService.sayHi("Hi");
    }

    @GetMapping("/circuit/breaker")
    public String circuitBreaker(){
        String url = "https://fakestoreapi.com/products/7";
        return restTemplate.getForObject(url, String.class);
    }
}
