package gosk.szymon.model.order;

import gosk.szymon.model.user.Person;

import java.util.List;

public record OrderBatchDTO(Person person, List<Order> orders) {
}
