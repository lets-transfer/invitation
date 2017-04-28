package lets.transfer.domain.team;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Team {

	@Id @GeneratedValue
	private long id;

	@Column(name = "team_id")
	private long teamId;

	private String teamName;
}
