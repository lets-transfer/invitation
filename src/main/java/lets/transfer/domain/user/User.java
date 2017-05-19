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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Orders> orders = new ArrayList<>();

    public void setProduct(String product){
        for(Orders order : orders){

        }
    }

}
