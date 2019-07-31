package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.type.Enabled;

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

    @Test
    public void testSelectRoleByUserIdChoose() {
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectRoleById(2L);
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
            List<SysRole> roles = roleMapper.selectRoleByUserIdChoose(1L);
            for (SysRole r : roles) {
                System.out.println("角色名: " + r.getName());
                if (r.getId().equals(1L)) {
                    Assert.assertNotNull(r.getPrivileges());
                } else if (r.getId().equals(2L)) {
                    Assert.assertNull(r.getPrivileges());
                    continue;
                }
                for (SysPrivilege privilege : r.getPrivileges()) {
                    System.out.println("权限名: " + privilege.getName());
                }
            }
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(2L);
            Assert.assertEquals(Enabled.enabled, role.getEnabled());
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
