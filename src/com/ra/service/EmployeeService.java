package com.ra.service;

import com.ra.entity.Employee;


import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee addProduct(Employee product);
    Employee findAny(Object... keys);
    Employee updateProduct(Employee product);
    List<Employee> findPagination(int startPosition, int maxResult);
}
