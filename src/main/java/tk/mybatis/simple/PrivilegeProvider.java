package tk.mybatis.simple;

import org.apache.ibatis.jdbc.SQL;

public class PrivilegeProvider {

    public String selectById(final Long id) {
        return new SQL() {
            {
                SELECT("sys_privilege_id id, privilege_name name, privilege_url url");
                FROM("t_sys_privilege");
                WHERE("sys_privilege_id = #{id}");
            }
        }.toString();
    }
}
