package gosk.szymon.model;

import gosk.szymon.core.error.OrderRequestErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ConversionResult {

    @Getter
    @Setter
    private OrderEntity orderEntity;

    private final List<OrderRequestErrorCode> errors = new ArrayList<>();

    public void addError(OrderRequestErrorCode error) {
        this.errors.add(error);
    }

    public boolean hasErrors() {
        return errors.size() != 0;
    }

}
