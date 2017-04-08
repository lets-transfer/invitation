package lets.transfer.controller;

import lets.transfer.domain.sample.Sample;
import lets.transfer.domain.sample.SampleService;
import lets.transfer.domain.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final SampleService sampleService;

    @Autowired
    public AdminController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewIndex(Model model) {
		model.addAttribute("samples",sampleService.list());
		return "admin/list";
	}

    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public String editSample(@PathVariable long id, Model model) {
		model.addAttribute("sample", sampleService.get(id));
		return "admin/insertEdit";
	}

	@RequestMapping(value ="/new", method = RequestMethod.GET)
	public String newSample(Model model) {
		model.addAttribute("template", new Template());
        return "admin/insertEdit";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String saveSample(@ModelAttribute Sample sample, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("result", "saved");
		sampleService.save(sample);
		return "redirect:/admin/";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteSample(@PathVariable long id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("result", "Deleted");
		sampleService.remove(id);
		return "redirect:/sample";
	}
}
