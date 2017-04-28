package lets.transfer.domain.team;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> list() {
        return teamRepository.findAll();
    }

    public Team save(Team team, MultipartFile file) {

        return teamRepository.save(team);
    }

    public Team get(long id) {
        return teamRepository.findOne(id);
    }

    public void remove(long id) {
        teamRepository.delete(id);
    }


}
