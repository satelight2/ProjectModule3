package com.ra.service;

import com.ra.entity.Bill;
import com.ra.entity.BillDetails;

import java.util.List;

public interface BillDetailService {
    List<BillDetails> findAll();
    BillDetails addBillDetails(BillDetails bill);
    BillDetails findbyID(Object key);
    BillDetails updateBillDetails(BillDetails bill);
    BillDetails searchProductByName(String name);
}
