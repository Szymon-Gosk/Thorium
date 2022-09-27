package gosk.szymon.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThoriumEvent<T> implements Event<EventType, T> {

    private EventType eventType;
    private T payload;

}
