package info.lostred.mapper;

import info.lostred.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int insert(Menu menu);

    int update(Menu menu);

    int deleteById(@Param("menuId") int menuId);

    List<Menu> selectByParentId(@Param("parentId") int parentId);

    List<Menu> selectAll();

    List<Menu> selectByRoleId(@Param("roleId") int roleId);
}
