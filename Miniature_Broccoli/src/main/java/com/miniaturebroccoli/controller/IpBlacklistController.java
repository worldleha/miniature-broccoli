package com.miniaturebroccoli.controller;

import com.miniaturebroccoli.annotation.JwtIgnore;
import com.miniaturebroccoli.pojo.IdList;
import com.miniaturebroccoli.pojo.IpBlacklist;
import com.miniaturebroccoli.service.IpBlacklistsService;
import org.springframework.web.bind.annotation.*;

/**
 * @author scc
 */
@RestController
@RequestMapping("/ipBlacklists")
public class IpBlacklistController {
    private final IpBlacklistsService ipBlacklistsService;

    public IpBlacklistController(IpBlacklistsService ipBlacklistsService) {
        this.ipBlacklistsService = ipBlacklistsService;
    }

    /**
     * 判断ip是否为在ip黑名单内
     * 返回1代表该ip在ip黑名单内 返回0代表该ip不在黑名单内
     **/
    @JwtIgnore
    @GetMapping("/{ipBlacklists}")
    public int isIpBlacklist(@PathVariable String ipBlacklists) {
       return ipBlacklistsService.isIpBlacklist(ipBlacklists);
    }

    /**
     * 添加ip黑名单
     */
    @JwtIgnore
    @PostMapping("")
    public String addIpBlacklist(@RequestBody IpBlacklist ipBlacklist) {
        return ipBlacklistsService.addIpBlacklist(ipBlacklist);

    }

    /**
     * 根据id删除ip黑名单
     */
    @JwtIgnore
    @DeleteMapping("/{id}")
    public String deleteIpBlacklist(@PathVariable Long id) {
        return ipBlacklistsService.deleteIpBlacklist(id);

    }

    /**
     * 修改ip黑名单数据
     */
    @JwtIgnore
    @PutMapping("")
    public String updateIpBlacklist(@RequestBody IpBlacklist ipBlacklist) {
        return  ipBlacklistsService.updateIpBlacklist(ipBlacklist);

    }

    /**
     * 分页返回ip黑名单数据
     */
    @JwtIgnore
    @GetMapping("/getIpBlacklistPage/{current}")
    public Object getIpBlacklistPage(@PathVariable int current) {
        return ipBlacklistsService.getIpBlacklistPage(current);
    }
    /**
     * 批量删除文章数据
     */
    @JwtIgnore
    @DeleteMapping ("/deleteIpBlacklistInBulk")
    public String deleteArticlesInBulk(@RequestBody IdList idList){
        return ipBlacklistsService.deleteIpBlacklistInBulk(idList);
    }
}
