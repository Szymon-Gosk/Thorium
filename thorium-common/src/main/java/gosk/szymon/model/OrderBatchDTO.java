package gosk.szymon.model;

import java.util.List;

public record OrderBatchDTO(Recipient recipient, List<MealOrder> orders) { }
