package lets.transfer.domain.memberInfo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class MemberInfo {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int age;
    private String address;
    private String phone;
}
