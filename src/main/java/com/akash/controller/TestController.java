package com.akash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.Batches.Batch;


@RestController
@RequestMapping("test")
public class TestController {
    
    @Autowired
    private Batch batch;

    @GetMapping("/")
    public String requestMethodName() {
       return batch.run();
    }
    

}
