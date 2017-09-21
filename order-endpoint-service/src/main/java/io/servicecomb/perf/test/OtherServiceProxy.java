package io.servicecomb.perf.test;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.Propagation;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.context.MethodTransactionContextEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtherServiceProxy {

    @Autowired
    HotelReservationService hotelReservationService;

    @Autowired
    FlightBookingService flightBookingService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    CarRentalService carRentalService;

    /*the propagation need set Propagation.SUPPORTS,otherwise the recover doesn't work,
      The default value is Propagation.REQUIRED, which means will begin new transaction when recover.
    */
    @Compensable(propagation = Propagation.SUPPORTS, confirmMethod = "reserve", cancelMethod = "reserve", transactionContextEditor = MethodTransactionContextEditor.class)
    public boolean reserve(TransactionContext transactionContext, String customerId) {
        return hotelReservationService.reserve(transactionContext, customerId);
    }

    @Compensable(propagation = Propagation.SUPPORTS, confirmMethod = "book", cancelMethod = "book", transactionContextEditor = MethodTransactionContextEditor.class)
    public boolean book(TransactionContext transactionContext, String customerId) {
        return flightBookingService.book(transactionContext, customerId);
    }

    @Compensable(propagation = Propagation.SUPPORTS, confirmMethod = "pay", cancelMethod = "pay", transactionContextEditor = MethodTransactionContextEditor.class)
    public boolean pay(TransactionContext transactionContext, String customerId) {
        return paymentService.pay(transactionContext, customerId);
    }

    @Compensable(propagation = Propagation.SUPPORTS, confirmMethod = "rent", cancelMethod = "rent", transactionContextEditor = MethodTransactionContextEditor.class)
    public boolean rent(TransactionContext transactionContext, String customerId) {
        return carRentalService.rent(transactionContext, customerId);
    }
}
