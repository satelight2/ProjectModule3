package com.ra.manager;

import com.ra.entity.Bill;

public interface Manager {
    void show(boolean type);
    void showDetails(boolean type);
    void add(boolean type);
    Bill searchBill(boolean type);
    void update(boolean type);
    void approve(boolean type);


}
