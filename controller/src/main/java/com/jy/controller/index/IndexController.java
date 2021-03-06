package com.jy.controller.index;

import com.jy.model.menu.Menu;
import com.jy.model.user.User;
import com.jy.service.menu.MenuService;
import com.jy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    /** @RequestMapping("")
    String toIndex(ModelMap modelMap){
        List<User> ulist = userService.selectUserList(new User());
        List<String> slist = new ArrayList<>();
        slist.add("李钟硕");
        slist.add("超可爱");
        slist.add("高颜值");
        slist.add("演技好");

        Map<String,Object> map = new HashMap<>();
        map.put("roomNum", "1234");
        map.put("roomType", "标间");
        map.put("roomPrice", "88.9");

        modelMap.addAttribute("userName","Lee");
        modelMap.addAttribute("userAge",32);
        modelMap.addAttribute("userIntro","7岁练成跆拳道黑带四段");
        modelMap.addAttribute("ulist",ulist);
        modelMap.addAttribute("slist",slist);
        modelMap.addAttribute("map",map);
        return "index";
    } */

    @RequestMapping("")
    String toIndex(ModelMap modelMap, Menu menu){
        //查询首页左侧导航列表
        List<Menu> list = menuService.selectIndexPageNavList(menu);
        modelMap.addAttribute("list",list);
        return "index";
    }

    @RequestMapping("noAuth")
    String toNoAuthPage() {
        return "noAuth";
    }

}
