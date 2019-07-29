package test.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import test.model.Country;
import tk.mybatis.simple.mapper.TestBaseMapper;

import java.util.List;

public class TestCountryMapper extends TestBaseMapper {

    @Test
    public void testSelectAll() {
        try (SqlSession sqlSession = getSqlSession()) {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            List<Country> countries = mapper.selectAll();
            countries
                    .stream()
                    .map(c -> String.format("%-4d%4s%4s", c.getId(), c.getName(), c.getCode()))
                    .forEach(System.out::println);
        }
    }

}
