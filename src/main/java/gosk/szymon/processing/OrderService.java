package gosk.szymon.processing;

import gosk.szymon.dev.DevTools;
import gosk.szymon.dev.RecipientRepository;
import gosk.szymon.messaging.EventType;
import gosk.szymon.messaging.ThoriumEvent;
import gosk.szymon.model.order.MealOrder;
import gosk.szymon.model.order.OrderBatchDTO;
import gosk.szymon.model.user.Recipient;
import gosk.szymon.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RecipientRepository recipientRepository;

    private final DevTools devTools;

    @Autowired
    public OrderService(OrderRepository orderRepository, RecipientRepository recipientRepository, DevTools devTools) {
        this.orderRepository = orderRepository;
        this.recipientRepository = recipientRepository;
        this.devTools = devTools;
    }

    public ThoriumEvent<String> createOrder(OrderBatchDTO orderBatch) {
        try {
            Recipient recipient = findRecipientFrom(orderBatch);
            orderBatch.orders().forEach(order -> saveOrder(order, recipient));
            return devTools.getOrders();
        } catch (Exception e) {
            e.printStackTrace();
            return ThoriumEvent
                    .<String>builder()
                    .eventType(EventType.FAILED_TO_SAVE_ORDER_BATCH)
                    .payload("Failed to save orders: " + e.getMessage())
                    .build();
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
