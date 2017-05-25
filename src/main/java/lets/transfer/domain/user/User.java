package lets.transfer.domain.user;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private long id;

    private String name;
    private String address;
    private String phone;
//    private String product;
//    private int amount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

}
