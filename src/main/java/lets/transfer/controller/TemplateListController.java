package lets.transfer.controller;

import lets.transfer.domain.templateList.TemplateListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templatelist")
public class TemplateListController {
	private final TemplateListService templateListService;


	@Autowired
	public TemplateListController(TemplateListService templateListService) {

		this.templateListService = templateListService;
	}

	@RequestMapping("/list")
	public String viewIndex(Model model) {
		model.addAttribute("samples", templateListService.list());
		return "templatelist/list";
	}
}
