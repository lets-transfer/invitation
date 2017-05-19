package lets.transfer.domain.membership;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> list() {
        return productRepository.findAll();
    }


    public Product save(Product product) {

        return productRepository.save(product);
    }

    public Product get(long id) {
        return productRepository.findOne(id);
    }

    public void remove(long id) {
        productRepository.delete(id);
    }


}
