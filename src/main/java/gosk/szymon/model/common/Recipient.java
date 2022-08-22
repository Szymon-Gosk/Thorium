package gosk.szymon.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Recipient {

    private final String name;
    private final String surname;
    private final RecipientType recipientType;
    private final EducationLevel educationLevel;

}
