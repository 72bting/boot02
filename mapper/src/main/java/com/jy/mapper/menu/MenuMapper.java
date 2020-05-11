package com.jy.mapper.menu;

import com.jy.model.menu.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

@Mapper
public interface MenuMapper {

    @Select("select m_id id," +
            " m_text name," +
            " m_href href," +
            " m_pid pid," +
            " m_type type" +
            " from t_menus" +
            " where m_pid = #{pid}")
    List<Menu> selectIndexPageNavList(Menu menu);

    @Select("SELECT  m1.m_href " +
            " FROM t_u_r_mids urmid" +
            " JOIN t_r_m_mids rmmid" +
            " ON urmid.t_role_id = rmmid.t_role_id" +
            " JOIN t_menus m1" +
            " ON rmmid.t_menu_id = m1.m_id" +
            " WHERE urmid.t_user_id = #{userID}" +
            " AND m1.m_href is not null" +
            " AND m1.m_href != ''")
    Set<String> selectMenuListByUserID(Menu menu);
}
