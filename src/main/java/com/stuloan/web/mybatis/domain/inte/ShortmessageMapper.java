package com.stuloan.web.mybatis.domain.inte;

import com.stuloan.web.mybatis.domain.Shortmessage;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortmessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shortmessage
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shortmessage
     *
     * @mbggenerated
     */
    int insert(Shortmessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shortmessage
     *
     * @mbggenerated
     */
    int insertSelective(Shortmessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shortmessage
     *
     * @mbggenerated
     */
    Shortmessage selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shortmessage
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Shortmessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table shortmessage
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Shortmessage record);
}