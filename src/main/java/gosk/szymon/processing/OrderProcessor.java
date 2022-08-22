package gosk.szymon.processing;

import gosk.szymon.model.ConversionResult;
import gosk.szymon.model.OrderDTO;
import gosk.szymon.model.OrderEntity;
import gosk.szymon.validation.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderProcessor implements Processor<OrderDTO, ConversionResult> {

    private final OrderValidator orderValidator;

    @Autowired
    public OrderProcessor(OrderValidator orderValidator) {
        this.orderValidator = orderValidator;
    }

    public ConversionResult process(OrderDTO orderDTO) {
        ConversionResult conversionResult = new ConversionResult();

        orderValidator.validate(orderDTO, conversionResult);
        conversionResult.setOrderEntity(convertToOrderEntity(orderDTO, UUID.randomUUID()));

        return conversionResult;
    }

    private OrderEntity convertToOrderEntity(OrderDTO orderDTO, UUID uuid) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setConfirmed(false);
        orderEntity.setUuid(uuid);

        orderEntity.setDate(orderDTO.getDate());
        orderEntity.setMealType(orderDTO.getMealType());
        orderEntity.setRecipient(orderDTO.getRecipient());

        return orderEntity;
    }

}
