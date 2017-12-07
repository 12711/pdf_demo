package com.example.demo;

import com.example.util.PdfFIleDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lsm on 2017/12/6.
 */
@RestController
public class TestController {
    @GetMapping(value = "/test")
    public String get() throws Exception{
        PdfFIleDemo.parse();
        return "test";
    }
}
