package com.arch.springcloud.controller;

import com.arch.springcloud.entities.Dept;
import com.arch.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService service;

    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept){
        return service.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}",method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = service.get(id);
        if(dept == null){
            throw new RuntimeException("ID："+id+" 对应的数据不存在");
        }
        return dept;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id){
        Dept errorDept = new Dept();
        errorDept.setDeptno(id);
        errorDept.setDname("ID："+id+" 对应的数据不存在,null--@HystrixCommand");
        errorDept.setDb_source("no this database in mysql");
        return errorDept;
    }

    @RequestMapping(value = "/dept/list",method = RequestMethod.GET)
    public List<Dept> list(){
        return service.list();
    }

}
