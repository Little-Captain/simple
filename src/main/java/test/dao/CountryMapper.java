package test.dao;

import java.util.List;
import test.model.Country;

public interface CountryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_country
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_country
     *
     * @mbggenerated
     */
    int insert(Country record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_country
     *
     * @mbggenerated
     */
    Country selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_country
     *
     * @mbggenerated
     */
    List<Country> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_country
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Country record);
}