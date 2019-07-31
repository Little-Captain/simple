package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser;

public class TestCache extends TestBaseMapper {

    @Test
    public void testL1Cache() {
        SysUser user1 = null;
        String name = "New Name";
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 查询 id = 1 的用户
            user1 = userMapper.selectById(1L);
            // 对当前获取的对象重新赋值
            user1.setUsername(name);
            // 再次查询获取 id 相同的用户
            SysUser user2 = userMapper.selectById(1L);
            // 虽然没有更新数据库，但是这个用户名和 user1 重新赋值的名字相同
            Assert.assertEquals(name, user1.getUsername());
            Assert.assertEquals(name, user2.getUsername());
            // 无论如何，user1 和 user2 完全就是同一个实例
            Assert.assertEquals(user1, user2);
        }
        System.out.println("开启新的 SqlSession");
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user2 = userMapper.selectById(1L);
            Assert.assertNotEquals(name, user2.getUsername());
            Assert.assertNotEquals(user1, user2);
            // 执行删除
            userMapper.deleteById(2L);
            // 获取 user3
            SysUser user3 = userMapper.selectById(1L);
            // 这里的 user2 user3 是两个不同的实例
            Assert.assertNotEquals(user2, user3);
        }

    }
}
