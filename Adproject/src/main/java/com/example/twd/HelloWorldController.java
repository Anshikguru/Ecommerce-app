package com.example.twd;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello") // base path to avoid conflicts
public class HelloWorldController {

    @GetMapping("/form")
    public String handleGet(@RequestParam(required = false) String name) {
        return (name != null && !name.isEmpty())
                ? "<h2>Hello, " + name + " (GET)</h2>"
                : "<p>No data received via GET.</p>";
    }

    @PostMapping("/form")
    public String handlePost(@RequestParam(required = false) String name) {
        return (name != null && !name.isEmpty())
                ? "<h2>Hello, " + name + " (POST)</h2>"
                : "<p>No data received via POST.</p>";
    }
}