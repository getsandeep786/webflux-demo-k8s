package com.learn.webfluxdemo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
public class Response {

    private Date date = new Date();
    private int output;

    public Response(int output) {
        System.out.println("Setting Response Object");
        this.output = output;
    }
}
