package io.servicecomb.perf.test;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {

    @Autowired
    OtherServiceProxy proxy;

    @Compensable(confirmMethod = "confirmMakeOrder", cancelMethod = "cancelMakeOrder")
    public boolean makeOrder(String customerId) {
        boolean r1 = proxy.pay(null, customerId);
        boolean r2 = proxy.book(null, customerId);
        boolean r3 = proxy.rent(null, customerId);
        boolean r4 = proxy.reserve(null, customerId);

        return r1 && r2 && r3 && r4;
    }

    public void confirmMakeOrder(String customerId) {

    }

    public void cancelMakeOrder(String customerId) {

    }
}
