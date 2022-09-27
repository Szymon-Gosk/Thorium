package gosk.szymon.gateway;

import gosk.szymon.messaging.OrderSenderService;
import gosk.szymon.model.OrderBatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {

    private final OrderSenderService orderSenderService;

    @Autowired
    public ViewController(OrderSenderService orderSenderService) {
        this.orderSenderService = orderSenderService;
    }

    @GetMapping("/view-orders")
    public String viewOrders() {
        return "";
    }

    @PostMapping(value="/place-order", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> placeOrder(@RequestBody OrderBatchDTO orderBatch) {
        return orderSenderService.send(orderBatch);
    }

}
