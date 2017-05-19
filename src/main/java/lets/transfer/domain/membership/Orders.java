package lets.transfer.domain.membership;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "MEMBERSHIP_ID")
    private MemberShip membership;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmount;
}
