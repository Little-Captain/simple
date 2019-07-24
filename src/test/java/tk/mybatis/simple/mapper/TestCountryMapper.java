package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.simple.model.Country;

import java.util.List;

public class TestCountryMapper extends TestBaseMapper {

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
            countryList
                    .stream()
                    .map(c -> String.format("%-4d%4s%4s", c.getId(), c.getName(), c.getCode()))
                    .forEach(System.out::println);
        } finally {
            // 不要忘记关闭 sqlSession
            sqlSession.close();
        }
    }
}
