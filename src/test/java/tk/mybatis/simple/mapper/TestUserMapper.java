package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;

public class TestUserMapper extends TestBaseMapper {

    @Test
    public void testSelectById() {
        try (SqlSession sqlSession = getSqlSession()) {
            // 获取 UserMapper 接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用 selectById 方法，查询 id = 1 的用户
            SysUser user = userMapper.selectById(1L);
            // user 不为空
            Assert.assertNotNull(user);
            // username = admin
            Assert.assertEquals("admin", user.getUsername());
        }
    }

    @Test
    public void testSelectAll() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> users = userMapper.selectAll();
            Assert.assertNotNull(users);
            Assert.assertTrue(users.size() > 0);
        }
    }

    @Test
    public void testSelectRolesByUserId() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roles = userMapper.selectRolesByUserId(1L);
            Assert.assertNotNull(roles);
            Assert.assertEquals(2, roles.size());
            Assert.assertEquals("admin", roles.get(0).getUser().getUsername());
            Assert.assertEquals("admin", roles.get(1).getUser().getUsername());
        }
    }
}
