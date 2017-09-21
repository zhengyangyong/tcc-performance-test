package io.servicecomb.perf.test;

import org.mengyun.tcctransaction.api.TransactionContext;

public interface CarRentalService {
    boolean rent(TransactionContext transactionContext, String customerId);
}
