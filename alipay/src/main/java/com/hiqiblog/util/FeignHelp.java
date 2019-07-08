package com.hiqiblog.util;


import com.hiqiblog.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author helloc
 * @Date 2019/7/5 16:30
 * @Version 1.0
 */
@FeignClient(
        //服务名
        name = "demo-service",
        //服务地址
        url = "https://cn.bing.com/"

)
public interface FeignHelp {
    /**
     * 对应的服务里的接口地址，及请求方式
     */
    @RequestMapping(value = "/HPImageArchive.aspx", method = RequestMethod.POST)
    @ResponseBody
    String feignPost(@RequestBody User user);

    @RequestMapping(value = "/HPImageArchive.aspx", method = RequestMethod.GET)
    String feignGet(@RequestParam(value = "format") String format, @RequestParam(value = "idx") String idx, @RequestParam(value = "n") String n, @RequestParam(value = "mkt") String mkt);

}
