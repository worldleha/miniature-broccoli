package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.pojo.SensitiveWord;
import com.miniaturebroccoli.service.SensitiveWordService;
import org.springframework.web.bind.annotation.*;

/**
 * @author scc
 */
@RestController
@RequestMapping("/sensitiveWord")
public class SensitiveWordController {
    private final SensitiveWordService dps;

    public SensitiveWordController(SensitiveWordService dps) {
        this.dps = dps;
    }

    /**
     * 添加敏感词数据
     */
    @PostMapping
    private Object addData(@RequestBody SensitiveWord dp) {
        return dps.addData(dp);
    }

    /**
     * 修改敏感词数据
     */
    @PutMapping
    private Object updateData(@RequestBody SensitiveWord dp) {
        return dps.updateData(dp);
    }

    /**
     * 删除敏感词数据
     */
    @DeleteMapping("/{id}")
    private Object deleteId(@PathVariable Long id) {
        return dps.deleteId(id);
    }

    /**
     * 获取敏感词数据
     */
    @JwtIgnore
    @GetMapping("/page/{current}")
    private Object getPage(@PathVariable Integer current) {
        return dps.getPage(current);
    }

    /**
     * 获取敏感词总条数
     */
    @JwtIgnore
    @GetMapping("/total")
    private Object total() {
        return dps.total();
    }

    /**
     * 批量删除文章数据
     */
    @JwtIgnore
    @DeleteMapping ("/deleteSensitiveWordInBulk")
    public String deleteArticlesInBulk(@RequestBody IdList idList){
        return dps.deleteSensitiveWordInBulk(idList);
    }
}
