package com.linzd.mobile;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
public class MobileApplicationTests {

    @Test
    public void contextLoads() {
        double num1= 6;
        double num2 = 4;
        double result = num1/num2;
        System.out.println(result);
    }



}
