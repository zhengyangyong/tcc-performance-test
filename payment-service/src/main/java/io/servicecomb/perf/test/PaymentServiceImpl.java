package io.servicecomb.perf.test;


import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.context.MethodTransactionContextEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentServiceImpl implements PaymentService {

    private double balance = 100000;

    private static final Logger log = LoggerFactory.getLogger("PaymentServiceImpl");

    @Compensable(confirmMethod = "confirmPay", cancelMethod = "cancelPay", asyncCancel = true, asyncConfirm = true, transactionContextEditor = MethodTransactionContextEditor.class)
    public boolean pay(TransactionContext transactionContext, String customerId) {

        log.info("Received payment request from customer {}", customerId);

        if ("anonymous".equals(customerId)) {
            return false;
        }

        if (balance < 0.0000001) {
            return false;
        }

        balance -= 0.0000001;
        return true;
    }

    public void confirmPay(TransactionContext transactionContext, String customerId) {

    }

    public void cancelPay(TransactionContext transactionContext, String customerId) {

    }
}
