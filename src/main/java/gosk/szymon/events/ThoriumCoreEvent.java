package gosk.szymon.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThoriumCoreEvent implements Event<String> {

    private EventType eventType;
    private String payload;

}
