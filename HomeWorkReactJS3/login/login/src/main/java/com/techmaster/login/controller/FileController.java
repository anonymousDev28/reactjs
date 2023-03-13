package com.techmaster.login.controller;

import com.techmaster.login.response.FileResponse;
import com.techmaster.login.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
public class FileController {
    @Autowired
    FileServiceImpl fileService;
    // upload file
    @PostMapping("files")
    public ResponseEntity<?> upLoadFile(@ModelAttribute("file")MultipartFile file){
        FileResponse fileResponse = fileService.uploadFile(file);
        return ResponseEntity.ok(fileResponse);
    }
    // xem tt file
    @GetMapping("files/{id}")
    public ResponseEntity<?> readFile(@PathVariable String id){
        byte[] bytes = fileService.readFile(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }
}
