package com.jy.mapper.role;

import com.jy.model.role.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface RoleMapper {


    @Select("SELECT r1.r_name " +
            " FROM t_roles r1" +
            " RIGHT JOIN(SELECT urmid.t_role_id" +
            " FROM t_u_r_mids urmid" +
            " WHERE urmid.t_user_id = #{userID}) r2" +
            " ON r1.r_id = r2.t_role_id")
    Set<String> selectRoleListByUserID(Role role);
}
