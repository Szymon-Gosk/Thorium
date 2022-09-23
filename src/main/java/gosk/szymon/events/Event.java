package gosk.szymon.events;

import java.io.Serializable;

public interface Event<T> extends Serializable {

    EventType getEventType();

    T getPayload();

}
