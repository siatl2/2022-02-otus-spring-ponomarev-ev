package ru.otus.homework18.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    @HystrixCommand(commandKey = "getIndexPage", fallbackMethod = "getErrorLoadPage")
    public String getIndexPage() {
        return "list";
    }

    public String getErrorLoadPage() {
        return "unavailable";
    }
}
