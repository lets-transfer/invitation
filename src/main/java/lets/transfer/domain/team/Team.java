package lets.transfer.domain.team;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Team {
	@Id @GeneratedValue
	private Long teamId;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	private List<Member> members;

	private String teamName;
}

/**
 *
 * Team --> Member
 *
 *
 *
 * Team Member
 *
 * Team team = new Team();
 * team.
 */
