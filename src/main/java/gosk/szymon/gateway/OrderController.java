package gosk.szymon.gateway;

import gosk.szymon.dev.DevOnly;
import gosk.szymon.dev.DevTools;
import gosk.szymon.model.MealOrder;
import gosk.szymon.model.common.EducationLevel;
import gosk.szymon.model.common.EducationType;
import gosk.szymon.model.common.Recipient;
import gosk.szymon.processing.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @DevOnly
    private final DevTools devTools;

    @Autowired
    public OrderController(OrderService orderService, DevTools devTools) {
        this.orderService = orderService;
        this.devTools = devTools;
    }

    @PostMapping(value="/place-order", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> createOrder(@RequestBody List<MealOrder> mealOrders) {
        return orderService.createOrder(mealOrders);
    }

    @DevOnly
    @PostMapping(value="/dev/post-recipients", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> createRecipients(@RequestBody List<Recipient> recipients) {
        return devTools.createRecipients(recipients);
    }

    @DevOnly
    @PostMapping(value="/dev/post-education-levels", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> createEducationLevels(@RequestBody List<EducationLevel> educationLevels) {
        return devTools.createEducationLevels(educationLevels);
    }

}
