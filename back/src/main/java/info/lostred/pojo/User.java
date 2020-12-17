package info.lostred.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String name;
    private String sex;
    private String mobilePhone;
    private String email;
    private Edu edu;
    private Prof prof;
    private Integer point;
    private Date regTime;
    private State state;
}