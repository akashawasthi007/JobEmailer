package com.akash.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.Batches.Batch;


@RestController
@RequestMapping("test")
public class TestController {
    
    @Autowired
    private Batch batch ;

    // @Autowired
    // public TestController(Batch batch) {
    //     this.batch = batch;
    // }

    @GetMapping("/")
    public String requestMethodName() throws IOException, MessagingException {
       return batch.singleMail();
    }
  
}
