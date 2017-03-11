package lets.transfer.invitation.template.service;

import lets.transfer.invitation.template.domain.InvitationTemplate;
import lets.transfer.invitation.template.repository.InvitationTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InvitationService {

	final InvitationTemplateRepository templateRepository;

	@Autowired
	public InvitationService(InvitationTemplateRepository templateRepository) {
		this.templateRepository = templateRepository;
	}

	public List<InvitationTemplate> fetchInvitationTemplates() {
		return templateRepository.findAll();
	}

}
