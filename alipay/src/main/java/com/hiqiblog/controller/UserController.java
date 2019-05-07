
package com.hiqiblog.controller;
import com.hiqiblog.entity.User;
import com.hiqiblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ww
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
        if(null != user){
            return user.getId()+"/"+user.getName()+"/";
        } else {
            return "null" ;
        }
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public String show(){
        List<User> userList=new ArrayList<User>();
        userList= userService.getAllList();
        if(null != userList){
            return "sas";
        }
        else{
            return "null";
        }
    }
}
