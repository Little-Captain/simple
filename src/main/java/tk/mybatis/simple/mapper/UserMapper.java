package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    SysUser selectById(Long id);

    List<SysUser> selectAll();

    List<SysRole> selectRolesByUserId(Long userId);

    int insert(SysUser sysUser);

    int insert2(SysUser sysUser);

    int insert3(SysUser sysUser);

    int updateById(SysUser sysUser);

    int deleteById(Long id);

    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

    List<SysRole> selectRolesByUserAndRole(@Param("user") SysUser user, @Param("role") SysRole role);

    List<SysUser> selectByUser(SysUser sysUser);

    int updateByIdSelective(SysUser sysUser);

    SysUser selectByIdOrUserName(SysUser sysUser);

    List<SysUser> selectByIdList(@Param("ids") List<Long> idList);

    int insertList(@Param("users") List<SysUser> users);

    int updateByMap(Map<String, Object> map);

    SysUser selectUserAndRoleById(Long id);

    SysUser selectUserAndRoleById2(Long id);

    SysUser selectUserAndRoleByIdSelect(Long id);

    List<SysUser> selectAllUserAndRoles();

    SysUser selectAllUserAndRolesSelect(Long id);

    void selectUserById(SysUser user);

    List<SysUser> selectUserPage(Map<String, Object> params);

    int insertUserAndRoles(@Param("user") SysUser user, @Param("roleIds") String roleIds);

    int deleteUserById(Long id);
}
