package gosk.szymon.processing;

import gosk.szymon.model.order.Order;
import gosk.szymon.model.order.OrderBatchDTO;
import gosk.szymon.model.user.Person;
import gosk.szymon.repositories.OrderRepository;
import gosk.szymon.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, PersonRepository personRepository) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
    }

    public ResponseEntity<String> createOrder(OrderBatchDTO orderBatch) {
        try {
            Person person = findPersonFrom(orderBatch);
            orderBatch.orders().forEach(order -> saveOrder(order, person));
            return ResponseEntity.ok("Created");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating persons: " + e.getMessage());
        }
    }

    private Person findPersonFrom(OrderBatchDTO orderBatch) throws Exception {
        return personRepository
                .findById(orderBatch.person().getId())
                .orElseThrow(Exception::new);
    }

    private void saveOrder(Order order, Person person) {
        order.setPerson(person);
        orderRepository.save(order);
    }

}
