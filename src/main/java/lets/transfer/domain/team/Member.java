package lets.transfer.domain.team;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Data
public class Member {
    @Id
    @GeneratedValue
    private long memberId;
    private int age;
    private String name;
    private String tname;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    public void setTeam(String tName) {
        if (tName != null) {
            log.debug("[ksk] tName not null: {}" , tName);
            this.team.setTeamName(tName);
        }
    }

}
