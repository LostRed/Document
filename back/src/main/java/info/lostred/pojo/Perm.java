package info.lostred.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perm {
    private Integer permId;
    private Integer roleId;
    private Integer menuId;
}
