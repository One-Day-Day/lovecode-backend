package cc.lovecode.web;

import cc.lovecode.dto.response.FileUploadResponse;
import cc.lovecode.enums.Role;
import cc.lovecode.exception.FileUploadException;
import cc.lovecode.jwt.JWTUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @PostMapping
    public FileUploadResponse upload(@RequestParam MultipartFile file, JWTUser jwtUser) {
        jwtUser.validateRoles(Role.SUPER_ADMIN);
        if (file.isEmpty()) {
            throw new FileUploadException("请不要上传空文件");
        }
        try {
            String fileId = UUID.randomUUID().toString();
            File targetFile = new File("/tmp", fileId);
            if (targetFile.createNewFile()) {
                file.transferTo(targetFile);
                return FileUploadResponse.builder()
                        .fileId(fileId)
                        .filename(file.getOriginalFilename())
                        .size(file.getSize())
                        .build();
            } else {
                throw new FileUploadException(String.format("文件(fileId=%s)创建失败", fileId));
            }
        } catch (IOException ex) {
            throw new FileUploadException(ex.getMessage());
        }
    }
}
