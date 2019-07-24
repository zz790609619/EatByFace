package com.hiqiblog.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author helloc
 * @Date 2019/7/5 16:30
 * @Version 1.0
 */
@FeignClient(
        //服务名
        name = "ip-service",
        //服务地址
        url = "http://ip.chinaz.com/"

)
public interface FeignIp {
    /**
     * 对应的服务里的接口地址，及请求方式
     */
    @RequestMapping(value = "/getIpInfo.php", method = RequestMethod.GET,produces = { "application/xml;charset=UTF-8" })
    @ResponseBody
    String feignGet();

}
