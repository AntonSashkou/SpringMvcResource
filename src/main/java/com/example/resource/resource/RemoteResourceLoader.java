package com.example.resource.resource;

import com.example.resource.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class RemoteResourceLoader implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getResource(String path) {
        var resource = resourceLoader.getResource(String.format("url:%s", path));


        try {
            return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            var errMessage = String.format("resource %s not found", path);
            log.error(errMessage);
            throw new ResourceNotFoundException(errMessage);
        }
    }

}
