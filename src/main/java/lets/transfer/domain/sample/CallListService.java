package lets.transfer.domain.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CallListService {
	private final SampleRepository sampleRepository;

	@Autowired
	public CallListService(SampleRepository sampleRepository) {
		this.sampleRepository = sampleRepository;
	}

	public List<Sample> list() {
		return sampleRepository.findAll();
	}
}
