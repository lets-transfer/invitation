package lets.transfer.controller;

import lets.transfer.domain.sample.CallListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class CallListController {
	private final CallListService callListService;


	@Autowired
	public CallListController(CallListService callListService) {

		this.callListService = callListService;
	}

	@RequestMapping("/calllist")
	public String viewIndex(Model model) {
		model.addAttribute("samples",callListService.list());
		return "sample/callList";
	}
}
