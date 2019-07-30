package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.simple.model.SysRole;

import java.util.List;

public interface RoleMapper {

    SysRole selectRoleById(Long id);

    List<SysRole> selectAllRoleAndPrivileges();

    @Select({"SELECT sys_role_id id, role_name name, enabled, create_by createBy, create_time createTime",
            "FROM t_sys_role ",
            "WHERE sys_role_id = #{id};"})
    SysRole selectById(Long id);

    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "sys_role_id", id = true),
            @Result(property = "name", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time"),
    })
    @Select({"SELECT sys_role_id, role_name, enabled, create_by, create_time",
            "FROM t_sys_role ",
            "WHERE sys_role_id = #{id};"})
    SysRole selectById2(Long id);

    @ResultMap("roleResultMap")
    @Select("SELECT * FROM t_sys_role;")
    List<SysRole> selectAll();

    @Insert({"INSERT INTO t_sys_role(sys_role_id, role_name, enabled, create_by, create_time)",
            "VALUE (#{id}, #{name}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP});"})
    int insert(SysRole sysRole);

    @Insert({"INSERT INTO t_sys_role(role_name, enabled, create_by, create_time)",
            "VALUE (#{name}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP});"})
    @Options(useGeneratedKeys = true)
    int insert2(SysRole sysRole);

    // 返回非自增主键
    @Insert({"INSERT INTO t_sys_role(role_name, enabled, create_by, create_time)",
            "VALUE (#{name}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP});"})
    @SelectKey(statement = "SELECT last_insert_id();",
            keyProperty = "id",
            resultType = Long.class,
            before = false)
    int insert3(SysRole sysRole);

    @Update({"UPDATE t_sys_role",
            "SET role_name = #{name}, enabled = #{enabled}, create_by = #{createBy}, create_time = #{createTime, jdbcType=TIMESTAMP}",
            "WHERE sys_role_id = #{id}"})
    int updateById(SysRole sysRole);

    @Delete("DELETE FROM t_sys_role WHERE sys_role_id = #{id}")
    int deleteById(Long id);
}
