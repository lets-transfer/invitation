package lets.transfer.domain.templateList;

import lets.transfer.domain.sample.Sample;
import lets.transfer.domain.templateList.TemplateList;
import lets.transfer.domain.sample.SampleRepository;
import lets.transfer.domain.templateList.TemplateListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TemplateListService {
	//private final SampleRepository sampleRepository;
	private final TemplateListRepository templateListRepository;

	@Autowired
	public TemplateListService(TemplateListRepository templateListRepository) {
		this.templateListRepository = templateListRepository;
	}

	public List<TemplateList> list() {
		return templateListRepository.findAll();
	}

	public TemplateList save(TemplateList template) {
		return templateListRepository.save(template);
	}
}
