package com.miniaturebroccoli.service.impl;

import com.miniaturebroccoli.mapper.AdminMapper;
import com.miniaturebroccoli.pojo.Admin;
import com.miniaturebroccoli.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author scc
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
