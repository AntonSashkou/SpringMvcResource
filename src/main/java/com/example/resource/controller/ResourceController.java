package com.example.resource.controller;

import com.example.resource.service.ResourceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService service;

    @GetMapping
    public String read(@NonNull @RequestParam String path) {
        return service.read(path);
    }
}
