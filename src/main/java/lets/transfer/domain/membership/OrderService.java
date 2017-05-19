package lets.transfer.domain.membership;

import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> list() {
        return orderRepository.findAll();
    }


    public Orders save(Orders order) {

        return orderRepository.save(order);
    }

    public Orders get(long id) {
        return orderRepository.findOne(id);
    }

    public void remove(long id) {
        orderRepository.delete(id);
    }


}
