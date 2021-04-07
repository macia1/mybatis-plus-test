package com.mybatisplus.training.quickstart.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Author 朝花夕誓
 * @Date 2021/3/29 11:04
 * @Version 1.0
 * @Description
 */
@Data
@ToString
public class User {
    // 配置自增id
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private String email;

//    @Version
    // 当 User 对象进行修改操作的时候会自动走MyMetaObjectHandler中的Update方法
    @TableField(fill = FieldFill.UPDATE)
    private Integer version;

    // 当 User 对象进行添加操作的时候会自动走MyMetaObjectHandler中的Insert方法
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    // 当 User 对象进行添加和修改的时候会自动走MyMetaObjectHandler中的对应的insert和update方法
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    private Boolean deleted;

}
