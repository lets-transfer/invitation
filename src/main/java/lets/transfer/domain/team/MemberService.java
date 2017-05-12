package lets.transfer.domain.team;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private TeamService teamService;

    @Autowired
    public MemberService(MemberRepository memberRepository, TeamService teamService) {
        this.memberRepository = memberRepository;
        this.teamService = teamService;
    }

    public List<Member> list() {
        return memberRepository.findAll();
    }

    public Member save(Member member, List<Team> teams) {

        String tName = member.getTname();


        Team newTeam = null;

        if (teams.isEmpty()) {
            log.debug("[ksk] team DB is null");

            newTeam = new Team();
            newTeam.setTeamName(member.getTname());
            member.setTeam(newTeam);
            saveTeam(newTeam);

        } else {
            log.debug("[ksk] team DB is exist");

            Team temp = null;
            int checkTeam = 0;
            for (Team t : teams) {
                if (t.getTeamName().equals(tName)) {
                    temp = t;
                    checkTeam++;
                }
            }

            if (checkTeam > 0) {
                log.debug("[ksk] already team");
                member.setTeam(temp);
            } else {
                log.debug("[ksk] no exist team");
                newTeam = new Team();
                newTeam.setTeamName(member.getTname());
                saveTeam(newTeam);
                member.setTeam(newTeam);
            }
        }

        return memberRepository.save(member);
    }

    public void saveTeam(Team team) {
        teamService.save(team);
    }

    public Member get(long id) {
        return memberRepository.findOne(id);
    }

    public void remove(long id) {
        memberRepository.delete(id);
    }


}
