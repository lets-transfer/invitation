package lets.transfer.domain.team;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Member {
	@Id @GeneratedValue
	private long id;
	private long teamId;
	private int age;
	private String name;
}
