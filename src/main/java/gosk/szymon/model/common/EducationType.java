package gosk.szymon.model.common;

import lombok.Getter;

import java.io.Serializable;

public enum EducationType implements Serializable {

    JUNIOR_HIGH_SCHOOL("gimnazjum"),
    HIGH_SCHOOL("liceum"),
    TEACHER("nauczyciel");

    @Getter
    private final String verbose;

    EducationType(String verbose) {
        this.verbose = verbose;
    }

}
