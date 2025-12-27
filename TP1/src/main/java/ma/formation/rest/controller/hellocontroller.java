package ma.formation.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class hellocontroller {
    @RequestMapping(value = { "/hello", "/" })
    public String hello() {
        System.out.println("ce package sera scanné par @SpringBootApplication");
        return "Hello World From my first API @RestController";
    }
}
