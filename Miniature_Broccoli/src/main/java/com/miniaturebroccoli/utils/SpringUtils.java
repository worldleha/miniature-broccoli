package com.miniaturebroccoli.utils;

import com.miniaturebroccoli.service.SensitiveWordService;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author scc
 */
@Component
public class SpringUtils {
    SensitiveWordService sensitiveWordService;
    public static SpringUtils springUtils;

    public SpringUtils(SensitiveWordService sensitiveWordService) {
        this.sensitiveWordService = sensitiveWordService;
    }

    @PostConstruct
    public void init() {
        springUtils = this;
        springUtils.sensitiveWordService = this.sensitiveWordService;
    }
}