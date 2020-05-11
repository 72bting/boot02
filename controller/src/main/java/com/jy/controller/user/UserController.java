package com.jy.controller.user;

import com.jy.model.user.User;
import com.jy.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("user/insert")
    @ResponseBody
    String insertUser(User user){
        userService.insertUser(user);
        return "{}";
    }

    @RequestMapping("toLogin")
    String toLoginPage(){
        return "login";
    }

    @RequestMapping("login")
    @ResponseBody
    Map<String,Object> toLogin(HttpServletRequest request, User user){
        Map<String,Object> map = new HashMap<>();
        map.put("code",2);
        HttpSession session = request.getSession();
        String randomcode = session.getAttribute("RANDOMVALIDATECODEKEY").toString();
        try {
            if (randomcode != null){
                if (randomcode.equalsIgnoreCase(user.getRandomCode())){
                    Subject subject = SecurityUtils.getSubject();
                    UsernamePasswordToken token = new UsernamePasswordToken(user.getUserAccount(), user.getUserPwd());
                    //使用subject来调用登陆方法
                    subject.login(token);
                    User u = (User) subject.getPrincipals().getPrimaryPrincipal();
                    if (null != u) {
                        //登陆成功
                        map.put("code", 1);
                        //向session中放一份用户信息
                        session.setAttribute("userInfo", u);
                    }
                }
            }
        } catch (AuthenticationException authenticationException) {
                map.put("code",0);
        } finally {
            return map;
        }
    }

}
