package gosk.szymon.processing;

import gosk.szymon.dev.RecipientRepository;
import gosk.szymon.model.MealOrder;
import gosk.szymon.model.OrderBatchDTO;
import gosk.szymon.model.common.Recipient;
import gosk.szymon.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RecipientRepository recipientRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, RecipientRepository recipientRepository) {
        this.orderRepository = orderRepository;
        this.recipientRepository = recipientRepository;
    }

    public ResponseEntity<String> createOrder(OrderBatchDTO orderBatch) {
        try {
            Recipient recipient = findRecipientFrom(orderBatch);
            orderBatch.orders().forEach(order -> saveOrder(order, recipient));
            return ResponseEntity
                    .ok("Orders saved");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error");
        }
    }

    private Recipient findRecipientFrom(OrderBatchDTO orderBatch) throws Exception {
        return recipientRepository
                .findById(orderBatch.recipient().getId())
                .orElseThrow(Exception::new);
    }

    private void saveOrder(MealOrder order, Recipient recipient) {
        order.setRecipient(recipient);
        orderRepository.save(order);
    }

}
