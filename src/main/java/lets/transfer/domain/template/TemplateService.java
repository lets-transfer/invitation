package lets.transfer.domain.template;

import lets.transfer.domain.sample.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@Transactional
public class TemplateService {
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

	public Template get(long id) {
		return templateRepository.findOne(id);
	}

	public void remove(long id) {
		templateRepository.delete(id);
	}

	public void upload(MultipartFile f) {

		String fName = f.getName();

		//templateRepository.upload(id);
	}
}
