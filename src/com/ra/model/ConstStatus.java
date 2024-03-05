package com.ra.model;

public interface ConstStatus {
    interface ProductStt {
        boolean ACTIVE = true;
        boolean INACTION = false;
    }
    interface BillStt {
        int CREATE = 0;
        int CANCEL = 1;
        int APPROVE = 2;
    }
    interface AccountStt {
        boolean ACTIVE = true;
        boolean BLOCK = false;
    }
    interface EmpStt {
        int ACTIVE = 0;
        int SLEEP = 1;
        int QUIT = 2;
    }
}
