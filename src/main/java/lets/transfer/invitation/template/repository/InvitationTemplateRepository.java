package lets.transfer.invitation.template.repository;

import lets.transfer.invitation.template.domain.InvitationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationTemplateRepository extends JpaRepository<InvitationTemplate, Long> {

}
