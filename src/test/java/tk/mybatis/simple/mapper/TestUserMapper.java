package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.*;

import static java.util.Arrays.asList;

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

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个 user 对象
            SysUser user = new SysUser();
            user.setUsername("test1");
            user.setPassword("123456");
            user.setEmail("test1@mybatis.tk");
            user.setInfo("test1 info");
            // 正常情况下应该读入y一张图片存到 byte 数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            // 将新建的对象插入数据库中，特别注意这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.insert(user);
            Assert.assertEquals(1, result);
            Assert.assertNull(user.getId());
        } finally {
            // 默认的 sqlSessionFactory.openSession() 是不自动提交的
            // 为了不影响其他测试，这里选择回滚
            // 如果真的需要插入数据，就需要提交
            sqlSession.rollback();
            // sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个 user 对象
            SysUser user = new SysUser();
            user.setUsername("test1");
            user.setPassword("123456");
            user.setEmail("test1@mybatis.tk");
            user.setInfo("test1 info");
            // 正常情况下应该读入y一张图片存到 byte 数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            // 将新建的对象插入数据库中，特别注意这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.insert2(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
        } finally {
            // 默认的 sqlSessionFactory.openSession() 是不自动提交的
            // 为了不影响其他测试，这里选择回滚
            // 如果真的需要插入数据，就需要提交
            sqlSession.rollback();
            // sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个 user 对象
            SysUser user = new SysUser();
            user.setUsername("test1");
            user.setPassword("123456");
            user.setEmail("test1@mybatis.tk");
            user.setInfo("test1 info");
            // 正常情况下应该读入y一张图片存到 byte 数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            // 将新建的对象插入数据库中，特别注意这里的返回值 result 是执行的 SQL 影响的行数
            int result = userMapper.insert3(user);
            Assert.assertEquals(1, result);
            Assert.assertNotNull(user.getId());
        } finally {
            // 默认的 sqlSessionFactory.openSession() 是不自动提交的
            // 为了不影响其他测试，这里选择回滚
            // 如果真的需要插入数据，就需要提交
            sqlSession.rollback();
            // sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("admin", user.getUsername());
            user.setUsername("admin_test");
            user.setEmail("admin_test@mybatis.tk");
            int result = userMapper.updateById(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1L);
            Assert.assertEquals("admin_test", user.getUsername());
        } finally {
            // sqlSession.commit();
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Assert.assertNotNull(userMapper.selectById(1L));
            Assert.assertEquals(1, userMapper.deleteById(1L));
            Assert.assertNull(userMapper.selectById(1L));
        } finally {
            // sqlSession.commit();
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roles = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size() > 0);
        }
    }

    @Test
    public void testSelectRolesByUserAndRole() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            SysRole role = new SysRole();
            role.setEnabled(1);
            List<SysRole> roles = userMapper.selectRolesByUserAndRole(user, role);
            Assert.assertNotNull(roles);
            Assert.assertTrue(roles.size() > 0);
        }
    }

    @Test
    public void testSelectByUser() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setUsername("ad");
            List<SysUser> users = userMapper.selectByUser(query);
            Assert.assertEquals(1, users.size());
            query = new SysUser();
            query.setEmail("test@mybatis.tk");
            users = userMapper.selectByUser(query);
            Assert.assertEquals(1, users.size());
            query = new SysUser();
            query.setUsername("ad");
            query.setEmail("test@mybatis.tk");
            users = userMapper.selectByUser(query);
            Assert.assertEquals(0, users.size());
        }
    }

    @Test
    public void testUpdateByIdSelective() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            user.setEmail("test@mybatis.tk");
            int result = userMapper.updateByIdSelective(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1L);
            Assert.assertEquals("admin", user.getUsername());
            Assert.assertEquals("test@mybatis.tk", user.getEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2Selective() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUsername("test-selective");
            user.setPassword("123456");
            user.setInfo("test info");
            user.setCreateTime(new Date());
            userMapper.insert2(user);
            user = userMapper.selectById(user.getId());
            Assert.assertEquals("test@mybatis.tk", user.getEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser query = new SysUser();
            query.setId(1L);
            query.setUsername("admin");
            SysUser user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            query.setId(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);
            query.setUsername(null);
            user = userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        }
    }

    @Test
    public void testSelectByIdList() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> users = userMapper.selectByIdList(asList(1L, 1001L, 1002L));
            Assert.assertEquals(2, users.size());
        }
    }

    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysUser> users = new ArrayList<>();
            for (int i = 0; i < 2; ++i) {
                SysUser user = new SysUser();
                user.setUsername("test" + i);
                user.setPassword("123456");
                user.setEmail("test@mybatis.tk");
                users.add(user);
            }
            int result = userMapper.insertList(users);
            Assert.assertEquals(2, result);
            users.stream().map(SysUser::getId).forEach(System.out::println);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("sys_user_id", 1L);
            map.put("user_email", "test@mybatis.tk");
            map.put("user_password", "12345678");
            userMapper.updateByMap(map);
            SysUser user = userMapper.selectById(1L);
            Assert.assertEquals("test@mybatis.tk", user.getEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById(1001L);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
        }
    }

    @Test
    public void testSelectUserAndRoleById2() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById2(1001L);
            Assert.assertNotNull(user);
            Assert.assertNotNull(user.getRole());
            System.out.println(user);
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleByIdSelect(1001L);
            Assert.assertNotNull(user);
            System.out.println("调用 user.hashCode()");
            System.out.println(user.hashCode());
            System.out.println("调用 user.getRole()");
            Assert.assertNotNull(user.getRole());
            System.out.println(user);
        }
    }
}
