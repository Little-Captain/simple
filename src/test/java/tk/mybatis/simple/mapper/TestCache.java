package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
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

    @Test
    public void testL2Cache() {
        SysRole role1;
        String newName = "New Name";
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            role1 = roleMapper.selectById(1L);
            role1.setName(newName);
            SysRole role2 = roleMapper.selectById(1L);
            // 虽然没有更新数据库，但是这个用户名和 role1 重新赋值的名字相同
            Assert.assertEquals(newName, role1.getName());
            Assert.assertEquals(newName, role2.getName());
            // 无论如何，role2 role1 完全就是同一个实例
            Assert.assertEquals(role1, role2);
        }
        // 关闭 session 时，SqlSession 中的一级缓存数据才会被保存到二级缓存中
        System.out.println("开启新的 sqlSession");
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role2 = roleMapper.selectById(1L);
            Assert.assertEquals(newName, role2.getName());
            // role2 和前一个 session 查询的结果是两个不同的实例
            Assert.assertNotEquals(role1, role2);
            SysRole role3 = roleMapper.selectById(1L);
            Assert.assertEquals(newName, role3.getName());
            // 这里的 role2 role3 是两个不同的实例
            Assert.assertNotEquals(role2, role3);
            // 二级缓存设置的是可读写的缓存, 内部通过序列化和反序列化来缓存数据
            // 当获取一个已缓存数据时，会得到一个新对象（通过反序列化生成）
            // 二级缓存设置的是只读的缓存, 内部通过 Map 来缓存数据
            // 当获取一个已缓存数据时，会得到同一个新对象
        }
        // 避免毫无意义的修改
        // 避免人为产生脏数据
        // 避免缓存和数据库的数据不一致
    }

    @Test
    public void testDirtyData() {
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleById(1001L);
            Assert.assertEquals("普通用户", user.getRole().getName());
            System.out.println("角色名: " + user.getRole().getName());
        }
        System.out.println("开启新的 session 1");
        try (SqlSession sqlSession = getSqlSession()) {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole role = roleMapper.selectById(2L);
            role.setName("脏数据");
            roleMapper.updateById(role);
            // 提交修改
            sqlSession.commit();
        }
        System.out.println("开启新的 session 2");
        try (SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysUser user = userMapper.selectUserAndRoleById(1001L);
            SysRole role = roleMapper.selectById(2L);
            Assert.assertEquals("普通用户", user.getRole().getName());
            Assert.assertEquals("脏数据", role.getName());
            System.out.println("角色名: " + user.getRole().getName());
            // 还原数据
            role.setName("普通用户");
            roleMapper.updateById(role);
            // 提交修改
            sqlSession.commit();
        }
    }
}
