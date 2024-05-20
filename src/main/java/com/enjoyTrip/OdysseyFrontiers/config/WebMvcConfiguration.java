package com.enjoyTrip.OdysseyFrontiers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.enjoyTrip.OdysseyFrontiers.interceptor.JWTInterceptor;

@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
public class WebMvcConfiguration implements WebMvcConfigurer {
	private JWTInterceptor jwtInterceptor;

	public WebMvcConfiguration(JWTInterceptor jwtInterceptor) {
		super();
		this.jwtInterceptor = jwtInterceptor;
	}

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
//    }

    // 아직 잘 모르겠음.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:5173", "http://localhost:5173/**", "http://localhost:5176", "http://localhost:5175", "http://localhost:5177", "http://localhost:5174") // Use allowedOriginPatterns
                .allowedMethods("*") // Allow all methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/assets/img/");
		registry.addResourceHandler("/*.html**").addResourceLocations("classpath:/static/");
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
