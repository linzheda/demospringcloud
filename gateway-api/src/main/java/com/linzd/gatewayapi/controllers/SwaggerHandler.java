package com.linzd.gatewayapi.controllers;

import com.linzd.gatewayapi.configs.SwaggerResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.*;

import java.util.List;

/**
 * 描述 wagger聚合接口，三个接口都是swagger-ui.html需要访问的接口
 *
 * @author Lorenzo Lin
 * @created 2019年12月25日 15:43
 */
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerHandler {

    private SwaggerResourceConfig swaggerResourceConfig;

    @Autowired
    public SwaggerHandler(SwaggerResourceConfig swaggerResourceConfig) {
        this.swaggerResourceConfig = swaggerResourceConfig;
    }

    @RequestMapping(value = "/configuration/security")
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/ui")
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(swaggerResourceConfig.get(), HttpStatus.OK);
    }


}

