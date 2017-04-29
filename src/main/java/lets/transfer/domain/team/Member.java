package lets.transfer.domain.team;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Member {
	@Id @GeneratedValue
	private long memberId;


	@ManyToOne
	@JoinColumn(name="teamId")
	private Team team;

	private int age;
	private String name;

}
