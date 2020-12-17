package info.lostred.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Integer menuId;
    private String menuName;
    private Integer parentId;
    private String iconClass;
    private String url;
}
