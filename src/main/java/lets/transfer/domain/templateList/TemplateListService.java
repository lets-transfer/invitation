package lets.transfer.domain.templateList;

import lets.transfer.domain.sample.Sample;
import lets.transfer.domain.sample.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TemplateListService {
	private final SampleRepository sampleRepository;

	@Autowired
	public TemplateListService(SampleRepository sampleRepository) {
		this.sampleRepository = sampleRepository;
	}

	public List<Sample> list() {
		return sampleRepository.findAll();
	}
}
