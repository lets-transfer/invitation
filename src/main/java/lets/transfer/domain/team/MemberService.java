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
    private final TeamRepository teamRepository;
    private TeamService teamService;
    private long modifyId;
    private List<Team> teams;

    @Autowired
    public MemberService(MemberRepository memberRepository, TeamRepository teamRepository, TeamService teamService) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    public List<Member> list() {
        return memberRepository.findAll();
    }

    public void saveModifyID(long id) {
        modifyId = id;
    }

    public Member save(Member member) {

        String tName = member.getTname();


        Team newTeam = null;

        teams = teamRepository.findAll();

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

                if (t.checkMember(modifyId)) {
                    log.debug("[ksk] modify team : " + modifyId);
                    temp = t;
                }
            }

            if (checkTeam > 0) {
                log.debug("[ksk] already team");
                member.setTeam(temp);
            } else if (modifyId != 0) {
                log.debug("[ksk] modify team name: " + temp.getTeamName() + " modify team: " + tName);
                newTeam = new Team();
                newTeam.setTeamName(tName);
                saveTeam(newTeam);
                member.setTeam(newTeam);
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

        Member member = memberRepository.findOne(id);
        Team team = null;
        teams = teamRepository.findAll();
        List<Member> members = memberRepository.findAll();

        int tCnt = 0;
        log.debug("[ksk] teams size " + teams.size());
        for (Team t : teams) {
            if (member.getTeam().getTeamName().equals(t.getTeamName())) {
                team = t;
                tCnt++;
            }
        }

        int mCnt = 0;
        for (Member m : members) {
            if (m.getTeam().getTeamName().equals(team.getTeamName())) {
                mCnt++;
            }
        }

        log.debug("[ksk] team tCnt :" + tCnt + " team info: " + team.getTeamName() + " mem mCnt: " + mCnt);

        memberRepository.delete(id);

        if (tCnt < 2 && mCnt < 2) {
            log.debug("[ksk] team object delete");
            teamService.remove(team.getTeamId());
        }
    }


}
