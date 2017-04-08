package lets.transfer.controller;

import lets.transfer.domain.template.Template;
import lets.transfer.domain.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/template")
public class TemplateController {
	private final TemplateService templateService;


	@Autowired
	public TemplateController(TemplateService templateService) {

		this.templateService = templateService;
	}

	@RequestMapping("/list")
	public String viewIndex(Model model) {
		model.addAttribute("templates", templateService.list());
		return "template/list";
	}

	@RequestMapping(value ="/new", method = RequestMethod.GET)
	public String newSample(Model model) {
		model.addAttribute("template", new Template());
		return "template/insertEdit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveSample(@ModelAttribute Template template, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("result", "saved");
		templateService.save(template);
		return "template/list";
	}
}
