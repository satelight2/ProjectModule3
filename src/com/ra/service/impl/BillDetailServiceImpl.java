package com.ra.service.impl;

import com.ra.entity.BillDetails;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.BillDetailService;

import java.util.List;

public class BillDetailServiceImpl implements BillDetailService {
     Repository<BillDetails> billDetailsRepository =new RepositoryImpl<>();

    @Override
    public List<BillDetails> findAll() {
        return this.billDetailsRepository.findAll(BillDetails.class);
    }

    @Override
    public BillDetails addBillDetails(BillDetails bill) {
        return this.billDetailsRepository.add(bill);
    }

    @Override
    public BillDetails findbyID(Object id) {
        return this.billDetailsRepository.findId(BillDetails.class, id);
    }

    @Override
    public BillDetails updateBillDetails(BillDetails bill) {
        return this.billDetailsRepository.edit(bill);
    }

    @Override
    public BillDetails searchProductByName(String name) {
        return null;
    }
}
