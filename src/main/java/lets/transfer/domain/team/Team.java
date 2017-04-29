package lets.transfer.domain.team;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Team {

	@Id @GeneratedValue
	private long teamId;

	@OneToMany(mappedBy="team",fetch = FetchType.EAGER)
	List<Member> members;

	private String teamName;
}
