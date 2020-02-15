package cc.lovecode.web;

import cc.lovecode.dto.response.FileUploadResponse;
import cc.lovecode.enums.Role;
import cc.lovecode.jwt.JWTUser;
import cc.lovecode.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/files")
public class FileStorageController {
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public FileUploadResponse upload(@RequestParam MultipartFile file, JWTUser jwtUser) {
        jwtUser.validateRoles(Role.SUPER_ADMIN);
        return fileStorageService.saveFile(file);
    }

    @GetMapping("/{fileId}/{filename}")
    public void download(@PathVariable String fileId,
                         @PathVariable String filename,
                         HttpServletResponse response,
                         JWTUser jwtUser) throws IOException {
        jwtUser.validateRoles(Role.SUPER_ADMIN);
        response.addHeader("Content-Disposition", "attachment; filename=" + filename);
        Files.copy(fileStorageService.getFile(fileId).toPath(), response.getOutputStream());
        response.getOutputStream()
                .flush();
    }
}
