package lets.transfer.controller;

import lets.transfer.domain.sample.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
	private final SampleService sampleService;

	@Autowired
	public UserController(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewIndex(Model model) {
		model.addAttribute("samples",sampleService.list());
		return "template/userList";
	}
}
