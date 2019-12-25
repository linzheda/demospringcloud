package com.linzd.mobile.feigns;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述 调用pc的微服务的接口
 *
 * @author Lorenzo Lin
 * @created 2019年12月10日 11:21
 */
@FeignClient(name = "pc", fallbackFactory = PcFeignClientFallback.class)
public interface PcFeignClient {
    @GetMapping("userCtr/getUserInfo")
    Object getUserInfo(@RequestParam("id") Integer id);

    @GetMapping("userCtr/getUserInfo/{id}")
    Object getUserInfo2(@PathVariable("id") Integer id);

}


@Component
@Slf4j
class PcFeignClientFallback implements FallbackFactory<PcFeignClient> {

    @Override
    public PcFeignClient create(Throwable throwable) {
        return new PcFeignClient() {
            @Override
            public Object getUserInfo(Integer id) {
                return throwable.getMessage();
            }

            @Override
            public Object getUserInfo2(Integer id) {
                System.out.println("进来了");
                return "服务降级";
            }
        };
    }


}