package io.servicecomb.perf.test;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.context.MethodTransactionContextEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.UUID;

import static java.util.Collections.singleton;

public class HotelReservationServiceImpl implements HotelReservationService {
    private final Set<String> customers = singleton("mike");

    private static final Logger log = LoggerFactory.getLogger("HotelReservationServiceImpl");

    @Compensable(confirmMethod = "confirmReserve", cancelMethod = "cancelReserve", transactionContextEditor = MethodTransactionContextEditor.class)
    public boolean reserve(TransactionContext transactionContext, String customerId) {

        log.info("Received hotel reserve request from customer {}", customerId);

        if (!customers.contains(customerId)) {
            return false;
        }

        UUID.randomUUID().toString();

        return true;
    }

    public void confirmReserve(TransactionContext transactionContext, String customerId) {

    }

    public void cancelReserve(TransactionContext transactionContext, String customerId) {

    }
}
