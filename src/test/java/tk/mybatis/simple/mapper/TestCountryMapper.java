package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import test.model.CountryExample;
import test.model.Country;

import java.util.List;

public class TestCountryMapper extends TestBaseMapper {

    @Test
    public void testSelectAll() {
//        try (SqlSession sqlSession = getSqlSession()) {
//            List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
//            countryList
//                    .stream()
//                    .map(c -> String.format("%-4d%4s%4s", c.getId(), c.getName(), c.getCode()))
//                    .forEach(System.out::println);
//        }
    }

    @Test
    public void testExample() {
        try (SqlSession sqlSession = getSqlSession()) {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample example = new CountryExample();
            // 设置排序规则, 这里使用具体的表字段
            example.setOrderByClause("country_id desc, country_name asc");
            // 设置是否 distinct 去重
            example.setDistinct(true);
            // 创建条件
            CountryExample.Criteria criteria = example.createCriteria();
            // id >= 1
            criteria.andIdGreaterThanOrEqualTo(1);
            // id < 4
            criteria.andIdLessThan(4);
            // code like '%U%'
            // 最容易出错的地方，注意 like 必须自己写上通配符的位置
            criteria.andCodeLike("%U%");
            // or 的情况
            CountryExample.Criteria or = example.or();
            // name = 中国
            or.andNameEqualTo("中国");
            // 执行查询
            List<Country> countries = mapper.selectByExample(example);
            countries
                    .stream()
                    .map(c -> String.format("%-4d%4s%4s", c.getId(), c.getName(), c.getCode()))
                    .forEach(System.out::println);
        }
    }

    @Test
    public void testUpdateByExampleSelective() {
        try (SqlSession sqlSession = getSqlSession()) {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample example = new CountryExample();
            CountryExample.Criteria criteria = example.createCriteria();
            criteria.andIdGreaterThan(2);
            Country country = new Country();
            country.setName("China");
            mapper.updateByExampleSelective(country, example);
            mapper.selectByExample(example)
                    .stream()
                    .map(c -> String.format("%-4d%4s%4s", c.getId(), c.getName(), c.getCode()))
                    .forEach(System.out::println);
        }
    }

    @Test
    public void testDeleteByExample() {
        try (SqlSession sqlSession = getSqlSession()) {
            CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample example = new CountryExample();
            CountryExample.Criteria criteria = example.createCriteria();
            criteria.andIdGreaterThan(2);
            mapper.deleteByExample(example);
            Assert.assertEquals(0, mapper.countByExample(example));
        }
    }
}
