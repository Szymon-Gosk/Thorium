package gosk.szymon.messaging;

import gosk.szymon.model.order.OrderBatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderSenderService {

    private static final String INPUT_TOPIC = "thorium-core-input-queue";

    private final StreamBridge streamBridge;

    @Autowired
    public OrderSenderService(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public ResponseEntity<String> send(OrderBatchDTO orderBatchDTO) {
        try {
            sendEvent(orderBatchDTO);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private void sendEvent(OrderBatchDTO orderBatchDTO) {
        streamBridge.send(INPUT_TOPIC, createEvent(orderBatchDTO));
    }

    private ThoriumEvent<OrderBatchDTO> createEvent(OrderBatchDTO orderBatchDTO) {
        return ThoriumEvent.<OrderBatchDTO>builder()
                .eventType(EventType.ORDERS_BATCH_REQUESTED)
                .payload(orderBatchDTO)
                .build();
    }

}
