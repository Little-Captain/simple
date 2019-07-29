package test1.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import test.model.CountryExample;
import test1.model.SysUser;
import test1.model.SysUserExample;
import tk.mybatis.simple.mapper.TestBaseMapper;

import java.util.List;

public class TestSysUserMapper extends TestBaseMapper {

    @Test
    public void testDeleteByPrimaryKey() {
        try (SqlSession sqlSession = getSqlSession()) {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = mapper.selectByPrimaryKey(1L);
            System.out.println(user);
        }
    }

    @Test
    public void testSelectByName() {
        try (SqlSession sqlSession = getSqlSession()) {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = mapper.selectByName("admin");
            System.out.println(user);
        }
    }

    @Test
    public void testExample() {
        try (SqlSession sqlSession = getSqlSession()) {
            SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
            SysUserExample example = new SysUserExample();
            // 设置排序规则, 这里使用具体的表字段
            example.setOrderByClause("sys_user_id desc, user_name asc");
            // 设置是否 distinct 去重
            example.setDistinct(true);
            // 创建条件
            SysUserExample.Criteria criteria = example.createCriteria();
            // id >= 1
            criteria.andIdGreaterThanOrEqualTo(1L);
            // id < 4
//            criteria.andIdLessThan(4L);
            // 执行查询
            List<SysUser> users = mapper.selectByExample(example);
            users
                    .stream()
                    .map(c -> String.format("%-4d%4s%4s", c.getId(), c.getName(), c.getEmail()))
                    .forEach(System.out::println);
        }
    }
}
