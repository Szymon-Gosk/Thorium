package gosk.szymon.model;

import gosk.szymon.model.common.Recipient;

import java.util.List;

public record OrderBatchDTO(Recipient recipient, List<MealOrder> orders) { }
