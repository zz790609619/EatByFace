
package com.hiqiblog.controller;
import com.hiqiblog.entity.User;
import com.hiqiblog.service.ISendEmailService;
import com.hiqiblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ww
 * User控制层
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ISendEmailService sendEmailService;
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public String show(User user){
//        User user=new User();
//        user.setId(id);
        user= userService.findUserById(user);
        if(null != user){
            return user.getId()+"/"+user.getName()+"/";
        } else {
            return "null" ;
        }
    }
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    @ResponseBody
    public String show(@RequestParam(value = "name")String name){
        User user=new User();
        user.setId(2);
        user.setName(name);
       int result= userService.insertUser(user);
        if(result != 0){
            return "success";
        } else {
            return "fail" ;
        }
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public String show(){
        List<User> userList= userService.getAllList();
        if(null != userList){
            return "sas";
        }
        else{
            return "null";
        }
    }
}
