package com.linzd.backsystem.feigns;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述 调用test的微服务的接口
 *
 * @author Lorenzo Lin
 * @created 2019年12月10日 11:21
 */
@FeignClient(name = "test", fallbackFactory = TestFeignClientFallback.class)
public  interface TestFeignClient {
    @GetMapping("test/testCtr/test")
    Object test(@RequestParam("id") Integer id);

    @GetMapping("test/userCtr/test2/{id}")
    Object test2(@PathVariable("id") Integer id);

}


@Component
@Slf4j
class TestFeignClientFallback implements FallbackFactory<TestFeignClient> {

    @Override
    public TestFeignClient create(Throwable throwable) {
        return new TestFeignClient() {
            @Override
            public Object test(Integer id) {
                System.out.println("服务降级");
                return "服务降级";
            }

            @Override
            public Object test2(Integer id) {
                System.out.println("服务降级");
                return throwable.getMessage();
            }
        };
    }


}