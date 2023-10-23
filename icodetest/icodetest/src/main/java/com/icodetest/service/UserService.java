package com.icodetest.service;

import com.icodetest.entity.User;
import com.icodetest.helper.ExcelHelper;
import com.icodetest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public void save(MultipartFile file) {
        try {
            List<User> user = ExcelHelper.convertExcelToListOfUsers(file.getInputStream());
            userRepository.saveAll(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ByteArrayInputStream load() {
        List<User> user = userRepository.findAll();

        return ExcelHelper.generateExcelTemplate(user);
    }


    public List<User> getAllFile() {
        return userRepository.findAll();
    }

}

