package com.arch.springcloud.service;

import com.arch.springcloud.entities.Dept;

import java.util.List;

public interface DeptService {
    boolean add(Dept dept);
    Dept    get(Long id);
    List<Dept> list();
}
