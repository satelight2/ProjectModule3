package com.ra.service.impl;

import com.ra.entity.Employee;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.EmployeeService;
import com.ra.util.annotation.Id;
import com.ra.util.annotation.Name;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    private Repository<Employee> employeeRepository;
    public EmployeeServiceImpl() {
        this.employeeRepository = new RepositoryImpl<>();
    }
    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll(Employee.class);
    }

    @Override
    public Employee addProduct(Employee product) {
        return this.employeeRepository.add(product);
    }

//    @Override
//    public Employee findbyID(String id) {
//        return this.employeeRepository.findId(Employee.class, id);
//    }

    @Override
    public Employee findAny(Object... keys) {
        return this.employeeRepository.findWithAnything(Employee.class, Id.class, Name.class, keys);
    }

    @Override
    public Employee updateProduct(Employee product) {
        return this.employeeRepository.edit(product);
    }

//    @Override
//    public Employee searchProductByNameOrID(String name) {
//        return this.employeeRepository.findByNameOrID(Employee.class, name);
//    }

    @Override
    public List<Employee> findPagination(int startPosition, int maxResult) {
        List<Employee> employees = this.employeeRepository.findByPagination(Employee.class, startPosition, maxResult);
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getEmpName)) // Sắp xếp theo tên nhân viên tăng dần
                .collect(Collectors.toList());
    }
}
