package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.pojo.Dataprocessing;
import com.miniaturebroccoli.service.dataProcessingService;

import org.springframework.web.bind.annotation.*;

/**
 * @author scc
 */
@RestController
@RequestMapping("/data")
public class DataprocessingController {
    private final dataProcessingService dps;

    public DataprocessingController(dataProcessingService dps) {
        this.dps = dps;
    }


    @PostMapping
    private Object adddata(@RequestBody Dataprocessing dp) {
        return dps.adddata(dp);
    }

    @PutMapping
    private Object updatedata(@RequestBody Dataprocessing dp) {
        return dps.updatedata(dp);
    }

    @DeleteMapping("/{id}")
    private Object deleteId(@PathVariable Long id) {
        return dps.deleteId(id);
    }

    @JwtIgnore
    @GetMapping("/page/{current}")
    private Object getPage(@PathVariable Integer current) {
        return dps.getPage(current);
    }

    @JwtIgnore
    @GetMapping("/total")
    private Object total() {
        return dps.total();
    }
}
