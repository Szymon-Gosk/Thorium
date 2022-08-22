package gosk.szymon.model;

import gosk.szymon.model.common.MealType;
import gosk.szymon.model.common.Recipient;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class OrderDTO {

    private LocalDate date;
    private MealType mealType;
    private Recipient recipient;

}
