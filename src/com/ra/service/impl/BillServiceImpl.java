package com.ra.service.impl;

import com.ra.entity.Bill;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.BillService;
import com.ra.util.annotation.BillCode;
import com.ra.util.annotation.Id;

import java.util.List;

public class BillServiceImpl implements BillService {
    private  Repository<Bill> billRepository = new RepositoryImpl<>();
    @Override
    public List<Bill> findAll() {
        return this.billRepository.findAll(Bill.class);
    }

    @Override
    public Bill addBill(Bill bill) {
        return this.billRepository.add(bill);
    }

    @Override
    public Bill findAny(Object keys) {
        return this.billRepository.findWithAnything(Bill.class, Id.class, BillCode.class,keys);
    }

    @Override
    public Bill updateBill(Bill bill) {
        return this.billRepository.edit(bill);
    }



}
