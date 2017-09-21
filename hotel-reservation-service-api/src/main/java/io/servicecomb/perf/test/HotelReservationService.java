package io.servicecomb.perf.test;

import org.mengyun.tcctransaction.api.TransactionContext;

public interface HotelReservationService {
    boolean reserve(TransactionContext transactionContext, String customerId);
}
