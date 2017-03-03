package lets.transfer.domain.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SampleService {
	private final SampleRepository sampleRepository;

	@Autowired
	public SampleService(SampleRepository sampleRepository) {
		this.sampleRepository = sampleRepository;
	}

	public Sample save(Sample sample) {
		return sampleRepository.save(sample);
	}

	public List<Sample> list() {
		return sampleRepository.findAll();
	}

	public Sample get(long id) {
		return sampleRepository.findOne(id);
	}

	public void remove(long id) {
		sampleRepository.delete(id);
	}

	public void remove(Sample sample) {
		sampleRepository.delete(sample);
	}
}
