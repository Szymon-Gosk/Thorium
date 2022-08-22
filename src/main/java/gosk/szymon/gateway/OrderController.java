package gosk.szymon.gateway;

import gosk.szymon.model.OrderDTO;
import gosk.szymon.processing.OrderService;
import gosk.szymon.receipt.ProcessingReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ProcessingReceipt createOrder(@RequestBody List<OrderDTO> orderDTOs) {
        return orderService.createOrder(orderDTOs);
    }

}
