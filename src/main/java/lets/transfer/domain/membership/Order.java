package lets.transfer.domain.membership;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue
    private long id;

    private int orderAmount;

    @ManyToMany
    @JoinColumn(name = "MEMBERSHIP_ID")
    private MemberShip membership;

    @ManyToMany
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
