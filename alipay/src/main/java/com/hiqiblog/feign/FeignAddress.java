package com.hiqiblog.feign;


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
        name = "address-service",
        //服务地址
        url = "http://ip.taobao.com/service"

)
public interface FeignAddress {
    /**
     * 对应的服务里的接口地址，及请求方式
     */
    @RequestMapping(value = "/getIpInfo.php", method = RequestMethod.GET)
    @ResponseBody
    String feignGet(@RequestParam(value = "ip") String ip);

}
