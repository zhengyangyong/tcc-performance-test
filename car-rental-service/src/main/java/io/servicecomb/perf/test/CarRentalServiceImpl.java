package io.servicecomb.perf.test;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.context.MethodTransactionContextEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

public class CarRentalServiceImpl implements CarRentalService {

    private static final Logger log = LoggerFactory.getLogger("CarRentalServiceImpl");

    private final Set<String> customers = new HashSet<String>(asList("mike", "snail"));
    private int delay = 60;

    @Compensable(confirmMethod = "confirmRent", cancelMethod = "cancelRent", transactionContextEditor = MethodTransactionContextEditor.class)
    public boolean rent(TransactionContext transactionContext, String customerId) {
        log.info("Received car rental request from customer {}", customerId);
        if (!customers.contains(customerId)) {
            return false;
        }

        if ("snail".equals(customerId)) {
            try {
                log.info("Encountered extremely slow customer {}", customerId);
                int timeout = delay;
                delay = 0;
                TimeUnit.SECONDS.sleep(timeout);
                log.info("Finally served the extremely slow customer {}", customerId);
            } catch (InterruptedException e) {
                return false;
            }
        }

        return true;
    }

    public void confirmRent(TransactionContext transactionContext, String customerId) {

    }

    public void cancelRent(TransactionContext transactionContext, String customerId) {

    }
}
