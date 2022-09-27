package gosk.szymon.repositories;

import gosk.szymon.model.order.MealOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<MealOrder, Long> { }
