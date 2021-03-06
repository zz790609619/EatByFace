
package com.hiqiblog.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hiqiblog.ViewModel.City;
import com.hiqiblog.feign.FeignAddress;
import com.hiqiblog.feign.FeignImage;
import com.hiqiblog.ViewModel.ImageDomain;
import com.hiqiblog.ViewModel.ResponseMessage;
import com.hiqiblog.entity.UpdateInfo;
import com.hiqiblog.entity.User;
import com.hiqiblog.feign.FeignIp;
import com.hiqiblog.service.ISendEmailService;
import com.hiqiblog.service.IUpdateInfoService;
import com.hiqiblog.service.IUserService;
import com.hiqiblog.util.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private FeignImage feignImage;
    @Autowired
    private FeignAddress feignAddress;
    @Autowired
    private FeignIp feignIp;


    @Autowired
    private IUpdateInfoService updateInfoService;
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public String show(User user){
        user= userService.findUserById(user);
        if(null != user){
            return user.getId()+"/"+user.getUsername()+"/";
        } else {
            return "null" ;
        }
    }
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage insert(@ModelAttribute User user){
        ResponseMessage<User> rm;
        rm = new ResponseMessage<>();
        List<User> isExistName= userService.findUserByUsername(user);
        if(isExistName.size()<=0){
            int result= userService.insertUser(user);
            if(result != 0){
                rm.setCode("1");
                rm.setMsg("欢迎来到新世界！");
                rm.setEntity(user);
                return rm;
            } else {
                rm.setCode("1001");
                rm.setMsg("注册失败!");
                return rm;
            }
        }else{
            rm.setCode("1001");
            rm.setMsg("用户名重复,请重新输入!");
            return rm;
        }

    }
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updateUserInfo(@ModelAttribute User user){
        ResponseMessage<User> rm;
        rm = new ResponseMessage<>();
        int result= userService.updateUserInfo(user);
        if(result != 0){
            rm.setCode("1");
            rm.setEntity(user);
            return rm;
        }
        else {
            rm.setCode("1001");
            rm.setMsg("补充信息失败!");
            return rm;
        }

    }
    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage loginUser(@ModelAttribute User user){
        ResponseMessage<User> rm;
        rm = new ResponseMessage<>();
        User result= userService.loginUser(user);
        if(result != null){
            rm.setCode("1");
            rm.setEntity(result);
            return rm;
        }
        else {
            rm.setCode("1001");
            rm.setMsg("账号密码错误!");
            return rm;
        }

    }

    @RequestMapping(value = "/getImageByBingJson",method = RequestMethod.GET)
    @ResponseBody
    public ImageDomain getImageByBingJson(){
        String jsonStr=feignImage.feignGet("js","0","1","zh-CN");
        JSONObject obj =  JSONObject.parseObject(jsonStr);
        ImageDomain entity=new ImageDomain();
        entity.setUrl(obj.getJSONArray("images").getJSONObject(0).getString("url"));
        entity.setTitle(obj.getJSONArray("images").getJSONObject(0).getString("copyright"));
        return entity;
    }
    @RequestMapping(value = "/isNeedUpdate",method = RequestMethod.GET)
    @ResponseBody
    public List<UpdateInfo> isNeedUpdate(){
        return  updateInfoService.getList();
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
    @RequestMapping(value = "/getAdress", method = RequestMethod.GET)
    @ResponseBody
    public City getIp(HttpServletRequest request) {
        String ip=IpUtil.getIpAddr(request);
            String local="0:0:0:0:0:0:0:1";
            if(local.equalsIgnoreCase(ip)){
                //ip = InetAddress.getLocalHost().getHostAddress();
                ip=IpUtil.getWebIP("http://ip.chinaz.com");
            }
            String result = feignAddress.feignGet("112.80.53.62");
            JSONObject obj =  JSONObject.parseObject(result);
            City city=obj.getJSONObject("data").toJavaObject(City.class);
            return city;

    }

}
