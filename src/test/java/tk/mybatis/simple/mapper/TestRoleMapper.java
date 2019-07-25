package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;

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
}
