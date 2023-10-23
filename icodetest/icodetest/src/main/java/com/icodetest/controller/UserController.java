package com.icodetest.controller;

import com.icodetest.entity.User;
import com.icodetest.helper.ExcelHelper;
import com.icodetest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userExcelService;


    @GetMapping("/user/download")
    public ResponseEntity<ByteArrayInputStream> downloadTemplate() {
        List<User> entities = userExcelService.getAllFile();
        ByteArrayInputStream resource = userExcelService.load();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=your_template.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


        @PostMapping("/user/upload")
        public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file) {
            System.out.println(file);
            if (ExcelHelper.hasExcelFormat(file)) {
                // if file format matched with Excel
                this.userExcelService.save(file);
                return ResponseEntity.ok(Map.of("message", "File is uploaded Successfully"));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file");
        }

        @GetMapping("/users")
        public List<User> getAllUsers() {

            return userExcelService.getAllFile();
        }

}


