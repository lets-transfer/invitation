package lets.transfer.domain.team;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Member {
	@Id @GeneratedValue
	private long id;


	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="team_id")
	private Collection<Team> teams;
	private int age;
	private String name;

	public Collection<Team> getTeam(){
		return teams;
	}
}
