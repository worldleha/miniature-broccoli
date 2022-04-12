package com.miniaturebroccoli.utils;

import com.miniaturebroccoli.service.dataProcessingService;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author scc
 */
@Component
public class SpringUtils {
    dataProcessingService dataProcessingService;
    public static SpringUtils springUtils;

    public SpringUtils(dataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    @PostConstruct
    public void init(){
        springUtils = this;
        springUtils.dataProcessingService = this.dataProcessingService;
    }
}