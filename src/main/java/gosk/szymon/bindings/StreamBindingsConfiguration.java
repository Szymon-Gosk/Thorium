package gosk.szymon.bindings;

import gosk.szymon.events.EventType;
import gosk.szymon.events.ThoriumCoreEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class StreamBindingsConfiguration {

    @Bean
    public Function<ThoriumCoreEvent, ThoriumCoreEvent> testFunction() {
        return event -> ThoriumCoreEvent.builder()
                .eventType(EventType.EXAMPLE)
                .payload(event.getPayload().toUpperCase())
                .build();
    }

}
