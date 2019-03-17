package com.yourtion.dubbo.one.server.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.yourtion.dubbo.one.api.enums.StatusCode;
import com.yourtion.dubbo.one.api.response.BaseResponse;
import com.yourtion.dubbo.one.api.service.IDubboItemServie;
import com.yourtion.dubbo.one.model.entity.ItemInfo;
import com.yourtion.dubbo.one.model.mapper.ItemInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author yourtion
 */
@Service(version = "1.0.0", validation = "true", timeout = 3000)
public class DubboItemService implements IDubboItemServie {

    private static final Logger log = LoggerFactory.getLogger(DubboItemService.class);

    @Autowired
    private ItemInfoMapper itemInfoMapper;

    /**
     * 列表查询服务-实际业务逻辑实现
     *
     * @return
     */
    public BaseResponse listItems() {
        BaseResponse response = new BaseResponse(StatusCode.Success);
        try {
            List<ItemInfo> iteminfo = itemInfoMapper.selectAll();
            log.info("Data", iteminfo);
            response.setData(iteminfo);
        } catch (Exception e) {
            log.error("listItems Error:", e.fillInStackTrace());
            response = new BaseResponse(StatusCode.Fail);
        }
        return response;
    }
}
