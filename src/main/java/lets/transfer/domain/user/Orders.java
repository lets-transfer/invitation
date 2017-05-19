package lets.transfer.domain.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private int orderAmount;

    public void setProduct(String product) {
        this.product.setProductName(product);
    }

    public void setOrderAmount(int amount) {
        this.orderAmount = amount;
    }

}
