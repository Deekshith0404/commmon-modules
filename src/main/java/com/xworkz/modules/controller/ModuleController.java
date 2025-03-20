package com.xworkz.modules.controller;

import com.xworkz.modules.dto.RegFormDto;
import com.xworkz.modules.entity.RegFormEntity;
import com.xworkz.modules.constrants.LocationEnum;
import com.xworkz.modules.passwordGen.PasswordGenerator;
import com.xworkz.modules.service.RegformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class ModuleController {
    private static final Logger log = LoggerFactory.getLogger(ModuleController.class);
    @Autowired
    RegformService regformService;

        ModuleController(){
            log.info("invoking controller");
        }

    @GetMapping("register")
    public String register(Model model){
        List<LocationEnum> list=new ArrayList<>(Arrays.asList(LocationEnum.values()));
        model.addAttribute("list",list);
        return "register.jsp";
    }

    @RequestMapping("updateprofile")
    public String updateForm(@RequestParam("email")String email,Model model){
        RegFormEntity regFormEntity =regformService.getIdByEmail(email);
        if (regFormEntity!=null){
            List<LocationEnum> list=new ArrayList<>(Arrays.asList(LocationEnum.values()));
            model.addAttribute("list",list);
            model.addAttribute("dto",regFormEntity);
            return "updatepage.jsp";
        }else {
            model.addAttribute("data","data not found");
            return "registrationsuccess.jsp";
        }

    }

    @PostMapping("regdata")
    public String RegformSubmit(@RequestBody RegFormDto regFormDto, Model model){
        System.out.println(regFormDto.getMultipartFile().getOriginalFilename());
        System.out.println(regFormDto.getProfile());
//           boolean isvalid= regformService.save(regFormDto,model);
//           if (isvalid){
//               model.addAttribute("dto",regFormDto);
//               return "registrationsuccess.jsp";
//           }else {
//               List<LocationEnum> list=new ArrayList<>(Arrays.asList(LocationEnum.values()));
//               model.addAttribute("list",list);
//               model.addAttribute("dto",regFormDto);
//               return "register.jsp";
//           }
            return "hello";
    }
    @RequestMapping("login")
    public String login(@RequestParam("usercap") String usercap,String captcha,String email,String password,Model model){
            if (usercap.equals(captcha)){
        int result1=regformService.login(email,password,model);
        if (result1==-1){
            model.addAttribute("email",email);
            return "setpassword.jsp";
        }else if (result1==1){
            RegFormEntity regFormEntity=regformService.getIdByEmail(email);
            model.addAttribute("dto",regFormEntity);
            model.addAttribute("email",email);
            return "registrationsuccess.jsp";
        }
        else {
            model.addAttribute("email",email);
            return "login.jsp";
        }
            }else {
                model.addAttribute("pass","invalid captcha please retry");
                return "startlogin";
            }

    }
    @RequestMapping("setpass")
    public String updatePass(String email,String password,Model model){
        boolean result=regformService.setPass(email,password);
        if (result){
            model.addAttribute("pass","password Saved");
            regformService.loginrest(email);
            return "login.jsp";
        }else {
            model.addAttribute("result","not saved");
            return "setpassword.jsp";
        }

    }
    @PostMapping("updateform")
    public String updateform(RegFormDto regFormDto,Model model){

        boolean isvalid= regformService.updateprofile(regFormDto,model);
        if (isvalid){
            model.addAttribute("dto",regFormDto);
            return "registrationsuccess.jsp";
        }else {
            List<LocationEnum> list=new ArrayList<>(Arrays.asList(LocationEnum.values()));
            model.addAttribute("list",list);
            model.addAttribute("dto",regFormDto);
            return "updatepage.jsp";
        }
    }
    @RequestMapping("deletebyemail")
    public  String deletebyemail(@RequestParam("email") String email,Model model){
            boolean deleted=regformService.deletebyemail(email);
            if (deleted){
                model.addAttribute("pass","user has been deleted ");
            }else {
                model.addAttribute("pass","data not found");
            }
            return "login.jsp";

    }
    @RequestMapping("startlogin")
    public String capthalogin(Model model){
        String captha=PasswordGenerator.generatePassword(6);
            model.addAttribute("captcha",captha);
            return "login.jsp";
    }
}
