package lets.transfer.domain.user;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class UserDto {
    private long id;
    private String name;
    private String address;
    private String phone;
    private String product;
    private int amount;
}
