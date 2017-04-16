package lets.transfer.domain.memberInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberInfoService {
    private final MemberInfoRepository memberInfoRepository;

    @Autowired
    public MemberInfoService(MemberInfoRepository memberInfoRepository) {
        this.memberInfoRepository = memberInfoRepository;
    }

    public MemberInfo save(MemberInfo memberInfo) {
        return memberInfoRepository.save(memberInfo);
    }

    public List<MemberInfo> list() {
        return memberInfoRepository.findAll();
    }

    public MemberInfo get(long id) {
        return memberInfoRepository.findOne(id);
    }

    public void remove(long id) {
        memberInfoRepository.delete(id);
    }

    public void remove(MemberInfo memberInfo) {
        memberInfoRepository.delete(memberInfo);
    }
}
