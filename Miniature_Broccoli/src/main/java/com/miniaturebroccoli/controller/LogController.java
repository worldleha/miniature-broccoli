package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.service.LogService;
import org.springframework.web.bind.annotation.*;

/**
 * @author scc
 */
@RestController
@RequestMapping("/log")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    /**
     * 分页返回日志信息
     */
    @JwtIgnore
    @GetMapping("/{current}")
    public Object getIpBlacklistPage(@PathVariable int current) {
        return  logService.getLogPage(current);
    }
    /**
     * 批量删除文章数据
     */
    @JwtIgnore
    @DeleteMapping("/deleteLogInBulk")
    public String deleteArticlesInBulk(@RequestBody IdList idList){
        return logService.deleteLogInBulk(idList);
    }
}
