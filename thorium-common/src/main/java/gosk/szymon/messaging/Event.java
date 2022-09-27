package gosk.szymon.messaging;

import java.io.Serializable;

public interface Event<E extends Enum<E>, T> extends Serializable {

    E getEventType();

    T getPayload();

}
