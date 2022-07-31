package com.sashkou.resource.controller;

import com.sashkou.resource.service.ResourceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService service;

    @GetMapping
    public String read(@NonNull @RequestParam String path) {
        log.info("controller");
        return service.read(path);
    }
}
