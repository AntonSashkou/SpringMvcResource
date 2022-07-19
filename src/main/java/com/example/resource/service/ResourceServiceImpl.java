package com.example.resource.service;

import com.example.resource.resource.RemoteResourceLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final RemoteResourceLoader resourceLoader;

    @Override
    public String read(String path) {
        return resourceLoader.getResource(path);
    }
}
