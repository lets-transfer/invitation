package lets.transfer.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public List<Orders> list() {
        return orderRepository.findAll();
    }


    public void save(UserDto userDto) {

        User user = new User();
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());
        user.setPhone(userDto.getPhone());

        userRepository.save(user);

        Product product = new Product();
        product.setProductName(userDto.getProduct());
        product.setAmount(userDto.getAmount());

        Orders order = orderRepository.findByUser(user);
        if (order == null) {
            order = new Orders();
            order.setOrderAmount(userDto.getAmount());
            order.setProduct(product);
            order.setUser(user);
            orderRepository.save(order);
        }

        productRepository.save(product);
//        return user;
    }

    public User get(long id) {
        return userRepository.findOne(id);
    }

    public void remove(long id) {
        userRepository.delete(id);
    }


}
