package com.xworkz.modules.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;
@Data
public class RegFormDto {
    private Integer id;
    @Null
    @Size(min = 3,max = 10,message = "the name should be above 3 and below 5")
    private String name;
    @Min(value = 3,message = "the id should be minimum 3")
    @Max(value = 10,message = "max should be 10")
    private String loginId;
    private String password;
    private String email;
    private Integer age;
    private Long phoneNumber;
    private String dateofBirth;
    private String location;
    private String profile;
    private MultipartFile multipartFile;


}
