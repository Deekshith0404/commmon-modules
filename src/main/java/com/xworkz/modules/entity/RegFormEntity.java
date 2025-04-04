package com.xworkz.modules.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@Entity
@Table(name = "reg_form")
@NamedQuery(name="getUserByemail" , query = "select e from RegFormEntity e where e.email=:email")
@NamedQuery(name = "setpassword",query = "UPDATE RegFormEntity u SET u.password = :password WHERE u.email =:email")
@NamedQuery(name = "logincountincrement",query = "UPDATE RegFormEntity u SET u.logincount = u.logincount +1  WHERE u.email =:email")
@NamedQuery(name = "loginreset",query = "update RegFormEntity u SET u.logincount = 0  WHERE u.email =:email")
@NamedQuery(name = "timelock",query = "update RegFormEntity u SET u.loginTimeOut =:time  WHERE u.email =:email")
@NamedQuery(name = "countEmailOccurrences",query = "SELECT COUNT(e.email) FROM RegFormEntity e WHERE e.email = :email")
@NamedQuery(name = "idcount",query = "SELECT COUNT(e.loginId) FROM RegFormEntity e WHERE e.loginId = :loginId")
@NamedQuery(name = "idcount",query = "SELECT COUNT(e.loginId) FROM RegFormEntity e WHERE e.loginId = :loginId")

@NamedQuery(
        name = "deletebyemail",
        query = "DELETE FROM RegFormEntity u WHERE u.email = :email"
)
public class RegFormEntity extends AbstractAuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String loginId;
    private String password;
    private String email;
    private Integer age;
    private Long phoneNumber;
    private String dateofBirth;
    private String location;
    private Integer logincount;
    private LocalDateTime loginTimeOut;
    private String profile;
}
