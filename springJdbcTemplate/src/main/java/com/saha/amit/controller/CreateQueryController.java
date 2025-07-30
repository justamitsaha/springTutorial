package com.saha.amit.controller;

import com.saha.amit.repository.CreateQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("create")
public class CreateQueryController {

    private final CreateQueryRepository query;

    @Autowired
    public CreateQueryController(CreateQueryRepository query) {
        this.query = query;
    }
}
