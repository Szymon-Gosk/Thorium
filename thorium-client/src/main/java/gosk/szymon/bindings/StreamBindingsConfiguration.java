package gosk.szymon.bindings;

import gosk.szymon.messaging.ThoriumEvent;
import gosk.szymon.model.OrderBatchDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class StreamBindingsConfiguration {

    @Bean
    public Supplier<ThoriumEvent<OrderBatchDTO>> postOrders() {
        return () -> null;
    }

}
