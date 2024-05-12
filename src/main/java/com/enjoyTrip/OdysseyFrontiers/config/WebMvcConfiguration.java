package com.enjoyTrip.OdysseyFrontiers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//
//        // TypeHandler 등록
//        TypeHandlerRegistry typeHandlerRegistry = factoryBean.getObject().getConfiguration().getTypeHandlerRegistry();
//        typeHandlerRegistry.register(com.enjoyTrip.OdysseyFrontiers.util.BoardType.class, BoardTypeHandler.class);
//
//        return factoryBean.getObject();
//    }
}
