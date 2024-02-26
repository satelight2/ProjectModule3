package com.ra.service;

import com.ra.entity.Employee;


import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee addProduct(Employee product);
    Employee findbyID(String id);
    Employee updateProduct(Employee product);
    Employee searchProductByNameOrID(String name);
    List<Employee> findPagination(int startPosition, int maxResult);
}
