package com.techmaster.practicerestapi.service;

import com.techmaster.practicerestapi.dto.PasswordDTO;
import com.techmaster.practicerestapi.dto.UserDTO;
import com.techmaster.practicerestapi.exception.BadRequestException;
import com.techmaster.practicerestapi.exception.NotFoundException;
import com.techmaster.practicerestapi.model.User;
import com.techmaster.practicerestapi.repository.UserRepository;
import com.techmaster.practicerestapi.response.FileResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    MailService mailService;
    @Autowired
    FileServiceImpl fileService;
    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(user -> new ModelMapper().map(user, UserDTO.class)).collect(Collectors.toList());
    }
    @Override
    public UserDTO getUserByName(String name) {
        Optional<User> user = userRepository.findUserByName(name);
        if(user.isEmpty()){
            throw new NotFoundException("user have name: "+name+" is not exist!");
        }
        return new ModelMapper().map(user.get(),UserDTO.class);
    }
    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = new ModelMapper().map(userDTO,User.class);
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public UserDTO getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("user have "+id+" is not exist!");
        }
        return new ModelMapper().map(user.get(),UserDTO.class);
    }

    @Override
    public UserDTO put(UserDTO userDTO) {
        Optional<User> existUser = userRepository.findById(userDTO.getId());
        if(existUser.isEmpty()){
            throw new NotFoundException("user is not exist!");
        }else {
            existUser.get().setAddress(userDTO.getAddress());
            existUser.get().setName(userDTO.getName());
//            existUser.get().setEmail(userDTO.getEmail());
            existUser.get().setPhone(userDTO.getPhone());
            existUser.get().setAvatar(userDTO.getAvatar());
            userRepository.save(existUser.get());
        }
        return new ModelMapper().map(userDTO,UserDTO.class);
    }

    @Override
    public String delete(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("user have "+id+" is not exist!");
        }
        userRepository.deleteById(id);
        return "Deleted user have id"+id;
    }

    @Override
    public UserDTO updateAvatar(UserDTO userDTO) {
        Optional<User> user = userRepository.findById(userDTO.getId());
        if(user.isEmpty()){
            throw new NotFoundException("user have "+userDTO.getId()+" is not exist!");
        }
        user.get().setAvatar(userDTO.getAvatar());
        userRepository.save(user.get());
        return new ModelMapper().map(user.get(),UserDTO.class);
    }
    public FileResponse updateAvatar2(Integer id, MultipartFile file){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("user have "+id+" is not exist!");
        }
        FileResponse fileResponse = fileService.uploadFile(file);
        user.get().setAvatar(fileResponse.getUrl());
        return fileResponse;
    }

    @Override
    public String updatePassword(int id,PasswordDTO passwordDTO) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("user is not exist!");
        }
        if(!user.get().getPassword().equals(passwordDTO.getOldPassword())){
            throw new BadRequestException("old password is not valid!");

        } else if(passwordDTO.getOldPassword().equals(passwordDTO.getNewPassword())){
            throw new BadRequestException("old password and new password cannot be same");
        } else {
            user.get().setPassword(passwordDTO.getNewPassword());
            userRepository.save(user.get());
        }
        return "success !!!";
    }

    @Override
    public String forgotPassword(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException("email is invalid!");
        }
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        user.get().setPassword(number+"");
        userRepository.save(user.get());
        return String.format("%06d", number);
//        mailService.sendEmail("maxnhon28496@gmail.com","forgot password","adasdasavava");
    }

}
