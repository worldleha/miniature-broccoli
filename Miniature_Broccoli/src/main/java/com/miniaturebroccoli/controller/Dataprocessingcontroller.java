package com.miniaturebroccoli.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.miniaturebroccoli.pojo.Dataprocessing;
import com.miniaturebroccoli.service.dataProcessingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author scc
 */
@RestController
public class Dataprocessingcontroller {
    private final dataProcessingService dps;

    public Dataprocessingcontroller(dataProcessingService dps) {
        this.dps = dps;
    }

    @GetMapping("/data")
    private List<String> data() {
        QueryWrapper<Dataprocessing> queryWrapper = new QueryWrapper<>();
        List<Dataprocessing> dataprocessings = dps.list(null);
        List<String> list = dataprocessings.stream().map(Dataprocessing::getSensitiveWords).collect(Collectors.toList());
        System.err.println(list);
        return list;
    }
}
