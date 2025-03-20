package com.xworkz.modules.service;

import com.xworkz.modules.dto.RegFormDto;
import com.xworkz.modules.entity.RegFormEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

public interface RegformService {
    Boolean save(RegFormDto regFormDto, Model model);
    RegFormEntity getIdByEmail(String email);
    int login(String email,String password,Model model);
    boolean setPass(String email,String password);
    boolean logincount(String email);
    boolean loginrest(String email);
    boolean updateprofile(RegFormDto regFormDto,Model model);
    boolean deletebyemail(String email);
    Long emailcount(String email);
    Long idcount(String loginId);
}
