package info.lostred.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin{
    private Integer adminId;
    private String username;
    private String password;
    private String name;
    private Role role;
    private State state;
}
