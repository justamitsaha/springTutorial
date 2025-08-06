package com.saha.amit.urimapping.controller;

import io.swagger.v3.oas.models.examples.Example;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/example")
@Validated
public class ExampleController {
    Logger logger = LoggerFactory.getLogger(ExampleController.class);


    @PostMapping("example")
    public String example1(@RequestBody List<String> example) {
        example.forEach(logger::info);
        return "Hello World";
    }

    @PostMapping("example/{var}")
    public String example2(@RequestBody List<String> example, @PathVariable String var) {
        logger.info(var);
        example.forEach(logger::info);
        return "Hello World";
    }

}
