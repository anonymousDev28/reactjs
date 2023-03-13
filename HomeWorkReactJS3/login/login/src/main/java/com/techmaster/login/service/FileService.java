package com.techmaster.login.service;

import com.techmaster.login.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileResponse uploadFile(MultipartFile file);

    byte[] readFile(String id);
}
