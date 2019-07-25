package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;

public class TestPrivilegeMapper extends TestBaseMapper {

    @Test
    public void testSelectById() {
        try (SqlSession sqlSession = getSqlSession()) {
            PrivilegeMapper mapper = sqlSession.getMapper(PrivilegeMapper.class);
            SysPrivilege privilege = mapper.selectById(1L);
            Assert.assertNotNull(privilege);
            Assert.assertEquals("用户管理", privilege.getName());
        }
    }
}
