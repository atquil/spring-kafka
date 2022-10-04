package com.atquil.springkafka.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Controller
public class StreamApiUsingStreamingResponseBody {
    
    /*
    StreamingResponseBody
        1. This is used for async request processing and content can be written 
            directly to the response OutputStream without holding up the threads in the servlet container.
        2. StreamingResponseBody timeout should be increased if you are returning a huge stream and your application throws a timeout exception
            spring.mvc.async.request-timeout = 2000000
    */

    //We are using ResponseEntity here which helps in better control of HTTP headers and status

    @GetMapping(value="/stream-data/stream-response-body")
    public ResponseEntity<StreamingResponseBody> streamData() {
        StreamingResponseBody responseBody = response -> {
            for (int i = 1; i <= 1000; i++) {
               try {
                  Thread.sleep(10);
                  response.write(("Data stream line - " + i + "\n").getBytes());
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
        };
        return ResponseEntity.ok()
           .contentType(MediaType.TEXT_PLAIN)
           .body(responseBody);
    }
}
