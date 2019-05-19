package com.lrhgroup.langlang.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户表的数据库操作接口
 * @author jyf
 * @version 1.0
 * @date 2019/5/19 16:18
 */
@Mapper
public interface UserMapper {

    @Select("select user_name from user where user_account=#{userAccount} and user_password=#{userPassword}")
    String queryLogin(@Param("userAccount") String userAccount, @Param("userPassword") String userPassword) throws Exception;

}
