package com.ra.service.impl;

import com.ra.entity.Bill;
import com.ra.entity.Product;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.BillService;

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
    public Bill findbyID(String id) {
        return null;
    }

    @Override
    public Bill updateBill(Bill bill) {
        return this.billRepository.edit(bill);
    }

    @Override
    public Bill searchProductByName(String name) {
        return null;
    }

    @Override
    public Bill findByBillCodeOrID(String any) {
        return this.billRepository.findByBillCodeOrID(Bill.class, any);
    }
}
