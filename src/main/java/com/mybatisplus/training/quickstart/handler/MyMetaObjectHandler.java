package com.mybatisplus.training.quickstart.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author 朝花夕誓
 * @Date 2021/3/29 13:35
 * @Version 1.0
 * @Description mybatis 自定义填充控制器
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    // insert 操作时要填充的字段
    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ...");
        // 根据属性名字设置要填充的值
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    // update 操作时要填充的字段
    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ... ");
        this.setFieldValByName("version", (int)metaObject.getValue("version") + 1,metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
