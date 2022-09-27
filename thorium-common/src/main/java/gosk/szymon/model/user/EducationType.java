package gosk.szymon.model.user;

import lombok.Getter;

public enum EducationType {

    JUNIOR_HIGH_SCHOOL("gimnazjum"),
    HIGH_SCHOOL("liceum"),
    TEACHER("nauczyciel");

    @Getter
    private final String verbose;

    EducationType(String verbose) {
        this.verbose = verbose;
    }

}
