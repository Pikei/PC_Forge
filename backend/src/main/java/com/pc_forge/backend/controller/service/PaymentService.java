package com.pc_forge.backend.controller.service;

import com.pc_forge.backend.controller.exceptions.PaymentException;
import com.pc_forge.backend.model.entity.order.Order;
import com.pc_forge.backend.model.entity.order.OrderDetail;
import com.pc_forge.backend.model.repository.order.OrderRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.model.checkout.Session;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Value("${frontend.url}")
    private String frontendUrl;

    private final OrderRepository orderRepository;

    public PaymentService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String createPaymentSession(Order order) throws PaymentException {
        var items = getOrderedProducts(order);
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setSubmitType(SessionCreateParams.SubmitType.PAY)
                .addAllLineItem(items)
                .setCustomerEmail(order.getUser().getEmail())
                .setSuccessUrl(frontendUrl + "/payment/success?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(frontendUrl + "/payment/cancel")
                .build();
        try {
            Session session = Session.create(params);
            order.setSessionId(session.getId());
            orderRepository.save(order);
            return session.getUrl();
        } catch (StripeException e) {
            throw new PaymentException("Could not establish payment session");
        }
    }

    private List<SessionCreateParams.LineItem> getOrderedProducts(Order order) {
        List<SessionCreateParams.LineItem> items = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            items.add(SessionCreateParams.LineItem.builder()
                    .setQuantity(orderDetail.getQuantity().longValue())
                    .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                            .setCurrency("pln")
                            .setUnitAmount((orderDetail.getProduct().getPrice().longValue() * 100))
                            .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                    .setName(orderDetail.getProduct().getName())
                                    .build())
                            .build())
                    .build());
        }
        return items;
    }

    public String refundPayment(Long orderId) throws PaymentException {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new PaymentException("Order does not exist");
        }

        Order order = optionalOrder.get();
        try {
            Session session = Session.retrieve(order.getSessionId());
            RefundCreateParams params = RefundCreateParams.builder()
                    .setPaymentIntent(session.getPaymentIntent())
                    .build();
            Refund refund = Refund.create(params);
            return refund.getAmount() / 100 + refund.getCurrency();
        } catch (StripeException e) {
            throw new PaymentException("Could not refund payment");
        }
    }
}
