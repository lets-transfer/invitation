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

    @Autowired
    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<User> list() {
        return userRepository.findAll();
    }


    public User save(UserDto membershipdto) {

        User membership = new User();
        membership.setName(membershipdto.getName());
        membership.setAddress(membershipdto.getAddress());
        membership.setPhone(membershipdto.getPhone());

        Product product = new Product();
        product.setProductName(membershipdto.getProduct());
        product.setAmount(membershipdto.getAmount());

        productRepository.save(product);

        return userRepository.save(membership);
    }

    public User get(long id) {
        return userRepository.findOne(id);
    }

    public void remove(long id) {
        userRepository.delete(id);
    }


}
