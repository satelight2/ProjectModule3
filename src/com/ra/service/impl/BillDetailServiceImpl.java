package com.ra.service.impl;

import com.ra.entity.BillDetails;
import com.ra.repository.Repository;
import com.ra.repository.impl.RepositoryImpl;
import com.ra.service.BillDetailService;
import com.ra.util.annotation.Id;

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
    public BillDetails findAny(Object key) {
        return this.billDetailsRepository.findWithAnything(BillDetails.class, Id.class,null,key);
    }

    @Override
    public BillDetails updateBillDetails(BillDetails bill) {
        return this.billDetailsRepository.edit(bill);
    }


}
