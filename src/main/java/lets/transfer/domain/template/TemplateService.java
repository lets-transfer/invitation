package lets.transfer.domain.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TemplateService {
	//private final SampleRepository sampleRepository;
	private final TemplateRepository templateRepository;

	@Autowired
	public TemplateService(TemplateRepository templateRepository) {
		this.templateRepository = templateRepository;
	}

	public List<Template> list() {
		return templateRepository.findAll();
	}

	public Template save(Template template) {
		return templateRepository.save(template);
	}
}
