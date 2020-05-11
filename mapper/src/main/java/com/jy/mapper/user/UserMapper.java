package com.jy.mapper.user;

import com.jy.model.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {


    @Insert("insert into t_users" +
            "(u_account,u_pwd)" +
            "values" +
            "(#{userAccount},#{userPwd})")
    void insertUser(User user);

    @Select("select " +
            " u_id userID, u_account userAccount, u_pwd userPwd" +
            " from t_users")
    List<User> selectUserList(User user);

    @Select("SELECT u_id userID, u_account userAccount, u_pwd userPwd" +
            " FROM t_users" +
            " WHERE u_account = #{userAccount}" +
            " AND u_pwd = #{userPwd}")
    User login(User user);
}
