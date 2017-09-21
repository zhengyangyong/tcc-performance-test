package io.servicecomb.perf.test;

import org.mengyun.tcctransaction.api.TransactionContext;

public interface FlightBookingService {
    boolean book(TransactionContext transactionContext, String customerId);
}
