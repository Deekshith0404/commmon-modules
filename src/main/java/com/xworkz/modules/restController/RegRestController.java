package com.xworkz.modules.restController;

import com.xworkz.modules.service.RegformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
public class RegRestController {
    @Autowired
    RegformService regformService;

    @GetMapping(value = "/email")
    public String onEmail(@RequestParam("email") String email ){
        System.out.println("invoking in the onEmail............");
        Long count=regformService.emailcount(email);
        if (count==0){
            return "";
        }
        return "email already exist";

    }

    @GetMapping(value = "/loginid/{loginid}",produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginid(@PathVariable String loginid){
        Long count1=regformService.idcount(loginid);
        if (count1==0){
            return "";
        }
        return "id already exist change id";
    }
}
