package lets.transfer.domain.team;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "teamId")
    private long teamId;

    private String teamName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<Member>();

    public void addMember(Member member) {
        this.members.add(member);

        if (member.getTeam() != this) {
            member.setTeam(this);
        }
    }

    public long getMemberId(long id) {

        long mId = 0;
        for (Member m : members) {
            if (m.getMemberId() == id) {
                mId = id;
                break;
            }
        }
        return mId;
    }

    public void removeTeam(List<Team> teams, Team team) {
        if (teams.contains(team)) {
            teams.remove(team);
        }
    }
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
