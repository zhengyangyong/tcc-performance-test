package io.servicecomb.perf.test;

import org.mengyun.tcctransaction.api.TransactionContext;

public interface PaymentService {
    public boolean pay(TransactionContext transactionContext, String customerId);
}
