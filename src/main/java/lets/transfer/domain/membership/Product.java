package lets.transfer.domain.membership;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private long id;
    private String productName;
}
