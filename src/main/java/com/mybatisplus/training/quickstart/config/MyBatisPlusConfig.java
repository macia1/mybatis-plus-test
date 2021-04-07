package com.mybatisplus.training.quickstart.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 朝花夕誓
 * @Date 2021/3/31 10:33
 * @Version 1.0
 * @Description mybatis-plus 乐观锁配置
 */
@Configuration
public class MyBatisPlusConfig {

    /**
     * mybatis-plus 版本信息控制插件
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInnerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

//    /**
//     * mybatis-plus 翻页控制插件老版
//     * @return
//     */
//    @Bean
//    public PaginationInterceptor paginationInnerInterceptor(){
//        return new PaginationInterceptor();
//    }


    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }


//    /**
//     * 启动mybatis-plus逻辑删除插件
//     * 好像不用注册也能使用，在yml配置文件中修改响应的全局配置就可以了
//     */
//    @Bean
//    public LogicDeleteByIdWithFill logicSqlInject(){
//        return new LogicDeleteByIdWithFill();
//    }


}
