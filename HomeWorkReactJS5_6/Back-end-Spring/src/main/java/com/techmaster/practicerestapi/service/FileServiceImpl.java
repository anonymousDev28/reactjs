package com.techmaster.practicerestapi.service;

import com.techmaster.practicerestapi.exception.BadRequestException;
import com.techmaster.practicerestapi.exception.NotFoundException;
import com.techmaster.practicerestapi.response.FileResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    private final double MAX_SIZES = 0;
    private final Path rootPath = Paths.get("uploads");
    public FileServiceImpl(){
        createFolder(rootPath.toString());
    }
    private void createFolder(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
    }
    @Override
    public FileResponse uploadFile(MultipartFile multipartFile) {
        validateFile(multipartFile);
        String fileId = UUID.randomUUID().toString();
        Path filePath = rootPath.resolve(fileId);
        File fileUpload = new File(rootPath.toString()+"/"+fileId);
        try(OutputStream outputStream = new FileOutputStream(fileUpload)) {
            outputStream.write(multipartFile.getBytes());
            return new FileResponse("api/v1/files/"+fileId);
        } catch (IOException e) {
            throw new RuntimeException("error upload file");
        }
    }
    public void validateFile(MultipartFile file){
        String fileName = file.getOriginalFilename();
        // ten file khong de torng
        if(fileName == null || fileName.isEmpty()){
            throw new BadRequestException("File invalid!");
        }
        // loai file hop ly khong

        String fileTail = getFileExtension(fileName);
        if(!isFileValid(fileTail)){
            throw new BadRequestException("File extension is invalid!");
        }
        // size nho hon pham vi cho phep
        double fileSize =(double) file.getSize() / (1024*1024);
        if(fileSize > 2){
            throw new BadRequestException("File is not over 2MB");
        }
    }
    public String getFileExtension(String file){
        int lastDotIndex = file.lastIndexOf(".");
        if(lastDotIndex == -1){
            return "";
        }
        return file.substring(lastDotIndex+1);
    }
    public boolean isFileValid(String fileExtension){
        List<String> fileExtensions = List.of("PNG","JPG");
        return fileExtensions.contains(fileExtension);
    }
    @Override
    public byte[] readFile(String id) {
        Path filePath = rootPath.resolve(id);
        File file = filePath.toFile();
        if(!file.exists()){
            throw new NotFoundException("Not found id = "+id);
        }
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException("error read file !!!");
        }
    }
}
