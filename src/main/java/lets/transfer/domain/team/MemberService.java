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
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> list() {
        return memberRepository.findAll();
    }

    public Member save(Member member) {

        return memberRepository.save(member);
    }

    public Member get(long id) {
        return memberRepository.findOne(id);
    }

    public void remove(long id) {
        memberRepository.delete(id);
    }


}
