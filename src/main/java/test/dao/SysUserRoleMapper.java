package test.dao;

import java.util.List;
import test.model.SysUserRole;

public interface SysUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbggenerated
     */
    int insert(SysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sys_user_role
     *
     * @mbggenerated
     */
    List<SysUserRole> selectAll();
}