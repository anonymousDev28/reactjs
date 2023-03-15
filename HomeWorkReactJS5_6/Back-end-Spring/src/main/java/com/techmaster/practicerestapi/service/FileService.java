package com.techmaster.practicerestapi.service;

import com.techmaster.practicerestapi.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    FileResponse uploadFile(MultipartFile file);

    byte[] readFile(String id);
}
