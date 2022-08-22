package gosk.szymon.model;

import gosk.szymon.model.common.MealType;
import gosk.szymon.model.common.Recipient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class OrderEntity {

    private int id;
    private UUID uuid;
    private boolean isConfirmed;

    private LocalDate date;
    private MealType mealType;
    private Recipient recipient;

}
