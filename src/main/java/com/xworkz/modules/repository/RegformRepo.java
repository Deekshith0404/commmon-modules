package com.xworkz.modules.repository;

import com.xworkz.modules.dto.RegFormDto;
import com.xworkz.modules.entity.RegFormEntity;

public interface RegformRepo {
    boolean save(RegFormEntity regFormEntity);
    RegFormEntity getProfileByEmail(String email);
    RegFormEntity login(String email);
    boolean setpassword(String email,String password);
    boolean logincount(String email);
    boolean loginrest(String email);
    boolean timelock(String email);
    Long emailcount(String email);
    boolean deleteByEmail(String email);
    Long loginidcount(String loginId);
    boolean updateform(RegFormEntity regFormEntity);
}
