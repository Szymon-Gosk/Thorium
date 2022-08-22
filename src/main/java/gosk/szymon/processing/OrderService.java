package gosk.szymon.processing;

import gosk.szymon.model.ConversionResult;
import gosk.szymon.model.OrderDTO;
import gosk.szymon.receipt.ProcessingReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderProcessor orderProcessor;

    @Autowired
    public OrderService(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    public ProcessingReceipt createOrder(List<OrderDTO> orderDTOs) {
        orderDTOs.parallelStream()
                .forEach(orderDTO -> {
                    ConversionResult conversionResult = orderProcessor.process(orderDTO);
                });

        return null;
    }



}
