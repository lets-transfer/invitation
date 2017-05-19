package lets.transfer.domain.membership;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class MemberShip {
    @Id
    @GeneratedValue
    @Column(name = "MEMBERSHIP_ID")
    private long id;

    private String name;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    private List<Order> orders;

}
