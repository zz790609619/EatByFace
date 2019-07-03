
package com.hiqiblog.controller;
import com.hiqiblog.ViewModel.ResponseMessage;
import com.hiqiblog.entity.User;
import com.hiqiblog.service.ISendEmailService;
import com.hiqiblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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
        user= userService.findUserById(user);
        if(null != user){
            return user.getId()+"/"+user.getName()+"/";
        } else {
            return "null" ;
        }
    }
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage insert(@ModelAttribute User user){
        ResponseMessage rm=new ResponseMessage();
        int result= userService.insertUser(user);
        if(result != 0){
            rm.setCode(1);
            rm.setMsg("欢迎来到新世界！");
            return rm;
        } else {
            rm.setCode(1001);
            rm.setMsg("注册失败!");
            return rm;
        }
    }
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<User>  show(){
        List<User> userList= userService.getAllList();
        if(null != userList){
            return userList;
        }
        else{
            return null;
        }
    }
}
