package gosk.szymon.gateway;

import gosk.szymon.dev.DevOnly;
import gosk.szymon.dev.DevTools;
import gosk.szymon.model.order.OrderBatchDTO;
import gosk.szymon.model.user.Recipient;
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

    @DevOnly
    private final DevTools devTools;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService, DevTools devTools) {
        this.orderService = orderService;
        this.devTools = devTools;
    }

    @PostMapping(value="/place-order", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createOrder(@RequestBody OrderBatchDTO orderBatch) {
        return orderService.createOrder(orderBatch);
    }

    @DevOnly
    @PostMapping(value="/dev/post-recipients", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createRecipients(@RequestBody List<Recipient> recipients) {
        return devTools.createRecipients(recipients);
    }

}