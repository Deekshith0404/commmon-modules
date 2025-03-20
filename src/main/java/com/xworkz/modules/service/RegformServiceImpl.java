package com.xworkz.modules.service;

import com.xworkz.modules.EmailSender.EmailSender;
import com.xworkz.modules.dto.RegFormDto;
import com.xworkz.modules.entity.RegFormEntity;
import com.xworkz.modules.passwordGen.PasswordGenerator;
import com.xworkz.modules.repository.RegformRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

@Service
public class RegformServiceImpl implements RegformService{
    boolean isvalid=true;

    @Autowired
    RegformRepo regformRepo;
    @Override
    public Boolean save(RegFormDto regFormDto, Model model) {
        RegFormEntity regFormEntity=new RegFormEntity();
//        BeanUtils.copyProperties(regFormDto,regFormEntity);
        if (regFormDto.getName()!=null && regFormDto.getName().length()>3 &&
            regFormDto.getName().length()<25 && regFormDto.getName().matches("[A-Z][a-z]*")){
            regFormEntity.setName(regFormDto.getName());
            Long result=regformRepo.emailcount(regFormDto.getEmail());
            if (result!=0){
                model.addAttribute("nameError","email already exist");
                isvalid=false;
            }
        }else {
            model.addAttribute("nameError","name should be between 3 to 25 and start with capital letter");
            isvalid=false;
        }
        if (regFormDto.getAge()>16){
            regFormEntity.setAge(regFormDto.getAge());

        }else {
            model.addAttribute("ageError","Age must be above 16");
            isvalid=false;
        }
        System.out.println(regFormDto.getPhoneNumber());
        String ph=(regFormDto.getPhoneNumber()!=null) ?regFormDto.getPhoneNumber().toString():"";
        if (regFormDto.getPhoneNumber()!=null && ph.matches("^[98]\\d{9}$") && ph.length()==10){
            regFormEntity.setPhoneNumber(regFormDto.getPhoneNumber());
        }else {
            model.addAttribute("phoneError","Phone number length must be 10 and number only");
            isvalid=false;
        }

        if (isvalid) {
            regFormEntity.setDateofBirth(regFormDto.getDateofBirth());
            regFormEntity.setLoginId(regFormDto.getLoginId());
            regFormEntity.setLogincount(-1);
            regFormEntity.setLocation(regFormDto.getLocation());
            regFormEntity.setEmail(regFormDto.getEmail());
            String password=PasswordGenerator.generatePassword(12);
            regFormEntity.setPassword(password);
            EmailSender.emailSender(regFormDto.getEmail(),password);
            regFormEntity.setCreatedBy(regFormDto.getName());
            regFormEntity.setCreatedTime(LocalDateTime.now());
            return regformRepo.save(regFormEntity);

        }
        else {
            return false;
        }
    }

    @Override
    public RegFormEntity getIdByEmail(String email) {
        return regformRepo.getProfileByEmail(email);
    }

    @Override
    public int login(String email, String password,Model model) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
        RegFormEntity regFormEntity=regformRepo.login(email);
        Boolean encryptpass=encoder.matches(password, regFormEntity.getPassword());
        if (regFormEntity!=null) {
            if (regFormEntity.getLogincount() == -1) {
                if (regFormEntity.getPassword().equals(password)) {
                    return -1;
                }else {
                    model.addAttribute("pass","password is wrong please check email and re enter");
                    return 8;
                }
            } else if (regFormEntity.getLogincount() == 3) {
                model.addAttribute("pass","you have completed your 3 change come back after 24hr");
                regformRepo.logincount(regFormEntity.getEmail());
                regformRepo.timelock(email);
                return 0;
            } else if (regFormEntity.getLogincount()>3) {
                LocalDateTime a= regFormEntity.getLoginTimeOut();
                LocalDateTime b=a.plusMinutes(2);
                LocalDateTime local=LocalDateTime.now();
                if (local.isAfter(b)){
                    regformRepo.loginrest(email);
                    model.addAttribute("pass","try now");
                }else {
                    model.addAttribute("pass","please try after the time out\t"+b);
                }
                return 6;

            } else if (encryptpass==false) {
                model.addAttribute("pass","password is not equal");
                regformRepo.logincount(email);
                return 3;
            } else if (encryptpass) {
                regformRepo.loginrest(email);
                return 1;
            }
        }else {
                model.addAttribute("pass","user not exist ");
                return 10;
            }
        return 2;
        }

    @Override
    public boolean setPass(String email,String password) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
        String pass=encoder.encode(password);
        return regformRepo.setpassword(email,pass);
    }

    @Override
    public boolean logincount(String email) {
        return regformRepo.logincount(email);
    }

    @Override
    public boolean loginrest(String email) {
        return regformRepo.loginrest(email);
    }

    @Override
    public boolean updateprofile(RegFormDto regFormDto, Model model) {
        RegFormEntity regFormEntity=new RegFormEntity();
        if (regFormDto.getName()!=null && regFormDto.getName().length()>3 &&
                regFormDto.getName().length()<25 && regFormDto.getName().matches("[A-Z][a-z]*")){
            regFormEntity.setName(regFormDto.getName());
        }else {
            model.addAttribute("nameError","name should be between 3 to 25 and start with capital letter");
            isvalid=false;
        }
        if (regFormDto.getAge()>16){
            regFormEntity.setAge(regFormDto.getAge());

        }else {
            model.addAttribute("ageError","Age must be above 16");
            isvalid=false;
        }
        System.out.println(regFormDto.getPhoneNumber());
        String ph=(regFormDto.getPhoneNumber()!=null) ?regFormDto.getPhoneNumber().toString():"";
        if (regFormDto.getPhoneNumber()!=null && ph.matches("^[98]\\d{9}$") && ph.length()==10){
            regFormEntity.setPhoneNumber(regFormDto.getPhoneNumber());
        }else {
            model.addAttribute("phoneError","Phone number length must be 10 and number only");
            isvalid=false;
        }

        if (isvalid) {
            regFormEntity.setDateofBirth(regFormDto.getDateofBirth());
            regFormEntity.setLoginId(regFormDto.getLoginId());
            regFormEntity.setLogincount(-1);
            regFormEntity.setLocation(regFormDto.getLocation());
            regFormEntity.setEmail(regFormDto.getEmail());
            regFormEntity.setUpdatedBy(regFormDto.getName());
            regFormEntity.setUpdatedTime(LocalDateTime.now());
            return regformRepo.save(regFormEntity);
        }
        else {
            return false;
        }
    }

    @Override
    public boolean deletebyemail(String email) {
        return regformRepo.deleteByEmail(email);
    }

    @Override
    public Long emailcount(String email) {
        return regformRepo.emailcount(email);
    }

    @Override
    public Long idcount(String loginId) {
        return regformRepo.loginidcount(loginId);
    }


}
