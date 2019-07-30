package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;

import javax.management.relation.Role;
import java.util.List;

public class TestRoleMapper extends TestBaseMapper {

    @Test
    public void testSelectById() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = mapper.selectById(1L);
            Assert.assertNotNull(role);
        }
    }

    @Test
    public void testSelectById2() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = mapper.selectById2(1L);
            System.out.println(role);
            Assert.assertNotNull(role);
        }
    }

    @Test
    public void testSelectAll() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roles = mapper.selectAll();
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size() > 0);
        }
    }

    @Test
    public void testSelectAllRoleAndPrivileges() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roles = roleMapper.selectAllRoleAndPrivileges();
            System.out.println("角色数: " + roles.size());
            for (SysRole role : roles) {
                System.out.println("角色名: " + role.getName());
                System.out.println(role.getCreateInfo());
                for (SysPrivilege privilege : role.getPrivileges()) {
                    System.out.println("权限名: " + privilege.getName());
                }
            }
        }
    }
}
