package com.alipay.demo.controller;

import com.alipay.demo.entity.User;
import com.alipay.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Song on 2017/2/15.
 * User控制层
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public String show(@RequestParam(value = "id")int id){
        User user=new User();
        user.setId(id);
        user= userService.findUserById(user);
        if(null != user)
            return user.getId()+"/"+user.getName()+"/";
        else return "null";
    }
}
