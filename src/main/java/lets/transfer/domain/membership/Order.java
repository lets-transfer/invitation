package lets.transfer.domain.membership;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue
    private long id;
    private int orderAmount;

    @ManyToOne
    @JoinColumn(name = "MEMBERSHIP_ID")
    private MemberShip membership;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
