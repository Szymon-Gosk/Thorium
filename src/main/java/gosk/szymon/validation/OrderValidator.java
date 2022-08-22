package gosk.szymon.validation;

import gosk.szymon.core.error.OrderRequestErrorCode;
import gosk.szymon.model.ConversionResult;
import gosk.szymon.model.OrderDTO;
import gosk.szymon.model.common.EducationLevel;
import gosk.szymon.model.common.MealType;
import gosk.szymon.model.common.Recipient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderValidator {

    public void validate(OrderDTO orderDTO, ConversionResult conversionResult) {
        validateDate(orderDTO.getDate(), conversionResult);
        validateMealType(orderDTO.getMealType(), conversionResult);
        validateRecipient(orderDTO.getRecipient(), conversionResult);
    }

    private void validateDate(LocalDate orderDate, ConversionResult conversionResult) {
        if (orderDate == null) {
            conversionResult.addError(OrderRequestErrorCode.MISSING_DATE);
            return;
        }

        LocalDate currentDate = LocalDate.now();
        if (orderDate.isBefore(currentDate.minusDays(1))) {
            conversionResult.addError(OrderRequestErrorCode.ORDER_DATE_IN_THE_PAST);
        }
        if (orderDate.isAfter(currentDate.plusDays(30))) {
            conversionResult.addError(OrderRequestErrorCode.ORDER_DATE_IN_THE_PAST);
        }
    }

    private void validateMealType(MealType mealType, ConversionResult conversionResult) {
        if (mealType == null) {
            conversionResult.addError(OrderRequestErrorCode.MISSING_MEAL_TYPE);
        }
    }

    private void validateRecipient(Recipient recipient, ConversionResult conversionResult) {

        if (isNameInvalid(recipient.getName())) {
            conversionResult.addError(OrderRequestErrorCode.INVALID_NAME);
        }
        if (isNameInvalid(recipient.getSurname())) {
            conversionResult.addError(OrderRequestErrorCode.INVALID_SURNAME);
        }
        if (recipient.getRecipientType() == null) {
            conversionResult.addError(OrderRequestErrorCode.MISSING_RECIPIENT_TYPE);
        }

        validateEducationLevel(recipient.getEducationLevel(), conversionResult);
    }

    private void validateEducationLevel(EducationLevel educationLevel, ConversionResult conversionResult) {
        if (educationLevel.getClassLevel().isPresent()) {
            int classLevel = educationLevel.getClassLevel().get();
            if (classLevel < 1 || classLevel > 3) {
                conversionResult.addError(OrderRequestErrorCode.INVALID_CLASS_LEVEL);
            }
        }

        if (educationLevel.getClassId().isPresent()) {
            String classId = educationLevel.getClassId().get();
            if ( ! classId.matches("^[ABC]$")) {
                conversionResult.addError(OrderRequestErrorCode.INVALID_CLASS_ID);
            }
        }
    }

    private boolean isNameInvalid(String name) {
        return StringUtils.isBlank(name) || ( ! isNameBetweenBounds(name));
    }

    private boolean isNameBetweenBounds(String string) {
        return string.length() >= 2 && string.length() <= 28;
    }

}
