package com.yourtion.dubbo.one.server.controller;

import com.yourtion.dubbo.one.api.enums.StatusCode;
import com.yourtion.dubbo.one.api.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yourtion
 */
@RestController
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    private static final String prefix = "base";

    @RequestMapping(value = prefix + "/one", method = RequestMethod.GET)
    public BaseResponse one(@RequestParam String param) {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            response.setData(param);
        } catch (Exception e) {
            e.printStackTrace();
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
}
