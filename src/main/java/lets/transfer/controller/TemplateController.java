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

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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
        byte[] bytes;
        boolean isSuccess = false;

        String user = null;
        user = System.getProperty("user.home");
        StringBuffer sb = new StringBuffer();

        String UPLOAD_PATH = sb.append(user).append("/").append(template.getName()).append("/").toString();

        log.debug("[ksk] UPLOAD_PATH {} ", UPLOAD_PATH);

        if (file != null) {
            try {

                Path path = Paths.get(UPLOAD_PATH);
                log.debug("[ksk] path: {} ", path);

                File dir = new File(UPLOAD_PATH);

                if (!dir.exists()) {
                    dir.mkdir();
                    log.debug("[ksk] dir Create Complete: {}", path);

                    isSuccess = fileWrite(file, path);

                } else {
                    isSuccess = fileWrite(file, path);
                }

                if (isSuccess == false) {
                    return returnFailPage();
                }


            } catch (Exception e) {
                e.printStackTrace();

                return returnFailPage();
            }
        } else {
            return returnFailPage();
        }

        template.setDate(getCurrentDate());
        template.setFilePath(UPLOAD_PATH);

        saveTemplate(template);

        return "redirect:/template";
    }

    private void saveTemplate(Template template) {
        templateService.save(template);
    }

    private String returnFailPage() {

        return "/template/failPage";

    }

    private boolean fileWrite(MultipartFile file, Path path) {

        File uploadFile = new File(path.toString() + "/" + file.getOriginalFilename());

        byte[] bytes = null;

        log.debug("[ksk] uploadFile: {}", uploadFile.toString());
        BufferedOutputStream bos = null;

        if (uploadFile == null) {
            return false;
        }

        try {
            bytes = file.getBytes();
            String fileType = file.getContentType();
            log.debug("[ksk] file Type: {}, byte length: {}", fileType, bytes.length);

            if (fileType.contains("zip")) {

                log.debug("[ksk] zip file write try");

                ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(bytes));
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(uploadFile));

                int size;
                while ((size = zis.read(bytes, 0, bytes.length)) != -1) {
                    zos.write(bytes, 0, size);
                }

                zis.close();
                zos.close();

            } else {
                log.debug("[ksk] normal file");
                bos = new BufferedOutputStream(new FileOutputStream(uploadFile));
                bos.write(bytes);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        log.debug("[ksk] file write complete");
        return true;
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
