package lets.transfer.domain.team;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue
    private Long teamId;
    private String teamName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member> members;

}

/**
 * Team --> Member
 * <p>
 * <p>
 * <p>
 * Team Member
 * <p>
 * Team team = new Team();
 * team.
 */
