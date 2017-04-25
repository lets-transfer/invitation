package lets.transfer.controller;

import lets.transfer.domain.template.Template;
import lets.transfer.domain.template.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Slf4j
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

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTemplate(Model model) {
        model.addAttribute("template", new Template());
        return "template/insertEdit";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String saveTemplate(@ModelAttribute Template template,
                               RedirectAttributes redirectAttributes,
                               @RequestParam(value = "file", required = false) MultipartFile file) {

        redirectAttributes.addFlashAttribute("result", "saved");


        template.setDate(getCurrentDate());

        saveTemplate(template,file);

        return "redirect:/template";
    }

    private void saveTemplate(Template template,MultipartFile file) {
        template = templateService.save(template,file);

        if(template.getflag() == false){
            returnFailPage();
        }
    }


    private Date getCurrentDate() {
        return new Date();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/failPage", method = RequestMethod.GET)
    public String failTemplate(@PathVariable long id, Model model) {
        return "template/failPage";
    }

    public static String returnFailPage() {
        return "/template/failPage";
    }

    /*@RequestMapping(value = "/template", method = RequestMethod.POST)
    public String templateUpload(@RequestPart("meta-data") MultipartFile file) {

        String fileName;
        File saveFile = null;

        if (!file.isEmpty()) {

            try {
                //byte[] bytes = file.getBytes();
                fileName = file.getOriginalFilename();

                saveFile.getParentFile().mkdir();

                return "redirect:/template";

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "redirect:/template/failPage";
    }*/
}
