package gosk.szymon.core.error;

public enum OrderRequestErrorCode {

    ORDER_DATE_IN_THE_PAST,
    ORDER_DATE_TOO_FAR_IN_THE_FUTURE,
    MISSING_DATE,
    MISSING_MEAL_TYPE,
    INVALID_NAME,
    INVALID_SURNAME,
    MISSING_RECIPIENT_TYPE,
    INVALID_CLASS_LEVEL,
    INVALID_CLASS_ID;

}
