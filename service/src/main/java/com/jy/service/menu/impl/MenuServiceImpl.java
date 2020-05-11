package com.jy.service.menu.impl;

import com.jy.mapper.menu.MenuMapper;
import com.jy.model.menu.Menu;
import com.jy.service.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectIndexPageNavList(Menu menu) {
            List<Menu> list = menuMapper.selectIndexPageNavList(menu);
            if(list != null && list.size() > 0){
                for (Menu ml : list){
                    Menu m2 = new Menu();
                    m2.setPid(ml.getId());
                    List<Menu> clist = selectIndexPageNavList(m2);
                    if(clist != null && 0 < clist.size()){
                        //把子节点集合放到父节点中
                        ml.setChildren(clist);
                    }
                }
            }
            return list;
        }

    @Override
    public Set<String> selectMenuListByUserID(Menu menu) {
        return menuMapper.selectMenuListByUserID(menu);
    }
}
