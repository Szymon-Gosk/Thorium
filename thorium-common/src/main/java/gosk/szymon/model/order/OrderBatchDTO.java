package gosk.szymon.model.order;

import gosk.szymon.model.user.Recipient;

import java.util.List;

public record OrderBatchDTO(Recipient recipient, List<MealOrder> orders) { }
