package lets.transfer.controller;

import lets.transfer.domain.sample.Sample;
import lets.transfer.domain.templateList.TemplateList;
import lets.transfer.domain.templateList.TemplateListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("templates", templateListService.list());
		return "templatelist/list";
	}

	@RequestMapping(value ="/new", method = RequestMethod.GET)
	public String newSample(Model model) {
		model.addAttribute("template", new TemplateList());
		return "templatelist/insertEdit";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveSample(@ModelAttribute TemplateList templateList, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("result", "saved");
		templateListService.save(templateList);
		return "redirect:/templatelist/list";
	}
}
