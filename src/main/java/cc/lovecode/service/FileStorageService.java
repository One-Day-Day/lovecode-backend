package cc.lovecode.service;

import cc.lovecode.dto.response.FileUploadResponse;
import cc.lovecode.exception.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageService {
    public FileUploadResponse saveFile(@RequestParam MultipartFile file) {
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

    public File getFile(String fileId) {
        return new File("/tmp", fileId);
    }
}
