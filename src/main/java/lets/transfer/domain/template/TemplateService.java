package lets.transfer.domain.template;

import lets.transfer.controller.TemplateController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
@Transactional
public class TemplateService {
    private final TemplateRepository templateRepository;

    @Autowired
    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    public List<Template> list() {
        return templateRepository.findAll();
    }


    public Template save(Template template, MultipartFile file) {

        byte[] bytes;
        boolean isSuccess = false;

        String user = null;
        user = System.getProperty("user.home");
        StringBuffer sb = new StringBuffer();

        String UPLOAD_PATH = sb.append(user).append("/").append(template.getName()).append("/").toString();

//        log.debug("[ksk] UPLOAD_PATH {} ", UPLOAD_PATH);

        if (file != null) {
            try {

                Path path = Paths.get(UPLOAD_PATH);
//                log.debug("[ksk] path: {} ", path);

                File dir = new File(UPLOAD_PATH);

                if (!dir.exists()) {
                    dir.mkdir();
                    //log.debug("[ksk] dir Create Complete: {}", path);

                    isSuccess = fileWrite(file, path);

                } else {
                    isSuccess = fileWrite(file, path);
                }

                if (isSuccess == false) {
                    template.setFlag(false);
                    return template;
                }

            } catch (Exception e) {
                e.printStackTrace();
                template.setFlag(false);
                return template;
            }
        } else {
            template.setFlag(false);
            return template;
        }

        template.setFilePath(UPLOAD_PATH);

        return templateRepository.save(template);
    }

    public Template get(long id) {
        return templateRepository.findOne(id);
    }

    public void remove(long id) {
        templateRepository.delete(id);
    }

    private boolean fileWrite(MultipartFile file, Path path) {

        File uploadFile = new File(path.toString() + "/" + file.getOriginalFilename());

        byte[] bytes = null;

//        log.debug("[ksk] uploadFile: {}", uploadFile.toString());
        BufferedOutputStream bos = null;

        if (uploadFile == null) {
            return false;
        }

        try {
            bytes = file.getBytes();
            String fileType = file.getContentType();
//            log.debug("[ksk] file Type: {}, byte length: {}", fileType, bytes.length);

//            if (fileType.contains("zip")) {
//
//                log.debug("[ksk] zip file write try");
//
//                ZipInputStream zis = new ZipInputStream(file.getInputStream());
//                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(uploadFile));
//
//                bos = new BufferedOutputStream(zos);
//                int size;
//                while ((size = zis.read(bytes, 0, bytes.length)) != -1) {
//                    bos.write(bytes, 0, size);
//                }
//
//                zis.close();
//                zos.close();
//
//            } else {
//            log.debug("[ksk] normal file");
            bos = new BufferedOutputStream(new FileOutputStream(uploadFile));
            bos.write(bytes);
//            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

//        log.debug("[ksk] file write complete");
        return true;
    }
}
