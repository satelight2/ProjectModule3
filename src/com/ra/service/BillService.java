package com.ra.service;

import com.ra.entity.Bill;
import com.ra.entity.Product;

import java.util.List;

public interface BillService {
    List<Bill> findAll();
    Bill addBill(Bill bill);
    Bill findAny(Object keys);
    Bill updateBill(Bill bill);


}
