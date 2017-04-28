package lets.transfer.domain.team;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Team {
	@Id
	private long teamId;
	private String teamName;
}
