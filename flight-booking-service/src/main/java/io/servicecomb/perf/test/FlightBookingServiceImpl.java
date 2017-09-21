package io.servicecomb.perf.test;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.context.MethodTransactionContextEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.UUID;

import static java.util.Collections.singleton;

public class FlightBookingServiceImpl implements FlightBookingService {
    private final Set<String> customers = singleton("mike");

    private static final Logger log = LoggerFactory.getLogger("FlightBookingServiceImpl");

    @Compensable(confirmMethod = "confirmBook", cancelMethod = "cancelBook", transactionContextEditor = MethodTransactionContextEditor.class)
    public boolean book(TransactionContext transactionContext, String customerId) {
        log.info("Received flight book request from customer {}", customerId);

        if (!customers.contains(customerId)) {
            return false;
        }

        UUID.randomUUID().toString();

        return true;
    }

    public void confirmBook(TransactionContext transactionContext, String customerId) {

    }

    public void cancelBook(TransactionContext transactionContext, String customerId) {

    }
}
