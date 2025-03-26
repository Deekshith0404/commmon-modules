package com.xworkz.modules.controller;

import com.xworkz.modules.dto.RegFormDto;
import com.xworkz.modules.entity.RegFormEntity;
import com.xworkz.modules.constrants.LocationEnum;
import com.xworkz.modules.passwordGen.PasswordGenerator;
import com.xworkz.modules.service.RegformService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

//    @RequestMapping(value = "updateprofile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @GetMapping("updateprofile")
    public String updateForm(@RequestParam("email")String email,Model model){
        RegFormEntity regFormEntity =regformService.getIdByEmail(email);
        if (regFormEntity!=null){
            List<LocationEnum> list=new ArrayList<>(Arrays.asList(LocationEnum.values()));
            model.addAttribute("list",list);
            model.addAttribute("dto",regFormEntity);
            model.addAttribute("pic",regFormEntity.getProfile());
            return "updatepage.jsp";
        }else {
            model.addAttribute("data","data not found");
            return "registrationsuccess.jsp";
        }

    }

    @PostMapping("reg")
    public String RegformSubmit(RegFormDto regFormDto, Model model) throws IOException {
//            String filename=regFormDto.getMultipartFile().getOriginalFilename();

        byte[] bytes=regFormDto.getMultipartFile().getBytes();
        Path path= Paths.get("E:\\commons\\"+regFormDto.getName() +System.currentTimeMillis());
        Files.write(path,bytes);
        String filename=path.getFileName().toString();
        regFormDto.setProfile(filename);
        System.out.println("this is file===========");
//        System.out.println(file.getOriginalFilename());
           boolean isvalid= regformService.save(regFormDto,model);
           if (isvalid){
               model.addAttribute("dto",regFormDto);
               return "registrationsuccess.jsp";
           }else {
               List<LocationEnum> list=new ArrayList<>(Arrays.asList(LocationEnum.values()));
               model.addAttribute("list",list);
               model.addAttribute("dto",regFormDto);
               return "register.jsp";
           }
    }

    @RequestMapping("login")
    public String login( String email,String password,String captcha,String usercap,Model model){
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
    @RequestMapping("updateform")
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

    @GetMapping("download")
    public void download(HttpServletResponse response,@RequestParam("profile") String profile) throws IOException {
            response.setContentType("image/jpg");
        File file=new File("E:\\commons\\"+profile);
        InputStream in=new BufferedInputStream(new FileInputStream(file));
        ServletOutputStream out = response.getOutputStream();
        IOUtils.copy(in,out);
        response.flushBuffer();

//        "E:\commons\Deekshit1742979555722"
    }
}
