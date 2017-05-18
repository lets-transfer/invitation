package lets.transfer.domain.membership;

import lets.transfer.domain.team.Member;
import lets.transfer.domain.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
@Transactional
public class MemberShipService {
    private final MemberShipRepository membershipRepository;

    @Autowired
    public MemberShipService(MemberShipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public List<MemberShip> list() {
        return membershipRepository.findAll();
    }


    public MemberShip save(MemberShip membership) {

        return membershipRepository.save(membership);
    }

    public MemberShip get(long id) {
        return membershipRepository.findOne(id);
    }

    public void remove(long id) {
        membershipRepository.delete(id);
    }


}
