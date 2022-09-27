package gosk.szymon.dev;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import gosk.szymon.model.order.MealOrder;
import gosk.szymon.model.user.Recipient;
import gosk.szymon.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@DevOnly
@Component
public final class DevTools {

    private final RecipientRepository recipientRepository;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public DevTools(RecipientRepository recipientRepository, OrderRepository orderRepository) {
        this.recipientRepository = recipientRepository;
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<String> createRecipients(List<Recipient> recipients) {
        try {
            recipientRepository.saveAll(recipients);
            return ResponseEntity
                    .ok("Recipients created");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating recipients: " + e.getMessage());
        }
    }

    public ResponseEntity<String> getOrders() {
        try {
            List<MealOrder> orders = orderRepository.findAll();
            return ResponseEntity.ok(parseOrdersToJson(orders));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not get orders: " + e.getMessage());
        }
    }

    private String parseOrdersToJson(List<MealOrder> orders) throws JsonProcessingException {
        objectMapper.findAndRegisterModules();
        String jsonOrder = objectMapper.writeValueAsString(orders);
        JsonElement jsonElement = JsonParser.parseString(jsonOrder);
        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(jsonElement);
    }

}
