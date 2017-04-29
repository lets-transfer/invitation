import lets.transfer.config.JpaConfig;
import lets.transfer.domain.team.Member;
import lets.transfer.domain.team.Team;
import lets.transfer.domain.team.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {JpaConfig.class})
public class JPATest {
	@Autowired
	private TeamRepository teamRepository;

	@Test
	@Rollback(false)
	public void test1() {
		Team team = new Team();
		team.setTeamName("Test");

		System.out.println("###"+team);

		teamRepository.save(team);

		System.out.println("###"+team);

		Team fetchedTeam = teamRepository.getOne(team.getTeamId());

		System.out.println("###"+fetchedTeam);

		System.out.println(fetchedTeam.getTeamName());

	}


	@Test
	@Rollback(false)
	public void test2() {
		Team team = new Team();
		team.setTeamName("myTeam");

		Member member1 = new Member();
		member1.setName("aaaaa");
		member1.setAge(10);
		member1.setTeam(team);

		Member member2 = new Member();
		member2.setName("bbbb");
		member2.setAge(20);
		member2.setTeam(team);

		team.setMembers(Arrays.asList(member1, member2));

		teamRepository.save(team);

		Long teamId = team.getTeamId();

		Team fetchedTeam = teamRepository.findOne(teamId);
		List<Member> members = fetchedTeam.getMembers();

		for (Member member : members) {
			System.out.println("# : "+member.getName());
		}

	}
}
