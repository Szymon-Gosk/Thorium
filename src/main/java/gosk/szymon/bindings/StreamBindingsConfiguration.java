package gosk.szymon.bindings;

import gosk.szymon.dev.DevTools;
import gosk.szymon.events.ThoriumEvent;
import gosk.szymon.model.OrderBatchDTO;
import gosk.szymon.processing.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class StreamBindingsConfiguration {

    private final OrderService orderService;

    @Autowired
    StreamBindingsConfiguration(OrderService orderService) {
        this.orderService = orderService;
    }

    @Bean
    public Function<ThoriumEvent<OrderBatchDTO>, ThoriumEvent<String>> thoriumFunction() {
        return event -> orderService.createOrder(event.getPayload());
    }

}
