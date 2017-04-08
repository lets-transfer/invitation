package lets.transfer.controller;

import lets.transfer.domain.sample.Sample;
import lets.transfer.domain.template.Template;
import lets.transfer.domain.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/template")
public class TemplateController {
	private final TemplateService templateService;


	@Autowired
	public TemplateController(TemplateService templateService) {

		this.templateService = templateService;
	}

	@RequestMapping("")
	public String viewIndex(Model model) {
		model.addAttribute("templates", templateService.list());
		return "template/list";
	}

	@RequestMapping(value ="/new", method = RequestMethod.GET)
	public String newTemplate(Model model) {
		model.addAttribute("template", new Template());
		return "template/insertEdit";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String saveTemplate(@ModelAttribute Template template, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("result", "saved");
		Date current = new Date();
		SimpleDateFormat sdf;

		try{
			sdf = new SimpleDateFormat("yyyy/MM/dd/hh:mm:ss");
			String date_s = sdf.format(current);
			Date date = sdf.parse(date_s);
			template.setDate(date);
		}catch (ParseException e){
			e.printStackTrace();
		}

		templateService.save(template);
		return "redirect:/template";
	}

	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public String editTemplate(@PathVariable long id, Model model) {
		model.addAttribute("template", templateService.get(id));
		return "template/insertEdit";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteTemplate(@PathVariable long id, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("result", "Deleted");
		templateService.remove(id);
		return "redirect:/template";
	}

	@RequestMapping(value="upload", method = RequestMethod.POST)
	public String templateUpload(@PathVariable long id, Model model, MultipartHttpServletRequest request){

		String fileName = request.getPathInfo();
		String path = request.getContextPath();
		File f = new File(path+"/"+fileName);
		templateService.upload(f,id);

		return "template/list";
	}
}
