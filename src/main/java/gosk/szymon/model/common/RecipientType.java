package gosk.szymon.model.common;

import lombok.Getter;

public enum RecipientType {

    JUNIOR_HIGH_SCHOOL("gimnazjum"),
    HIGH_SCHOOL("liceum"),
    TEACHER("nauczyciel");

    @Getter
    private final String verbose;

    RecipientType(String verbose) {
        this.verbose = verbose;
    }

}
