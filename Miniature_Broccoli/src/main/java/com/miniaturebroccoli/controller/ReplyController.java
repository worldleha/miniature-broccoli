package com.miniaturebroccoli.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author scc
 */
@CrossOrigin
@RestController
@RequestMapping("/replyform")
@Api(tags = "回复接口")
public class ReplyController {

    @GetMapping()
    private Object getClassify() {
        return 10 / 0;
    }
}