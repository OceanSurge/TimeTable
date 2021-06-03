package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//全集配置跨域请求
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//	@Autowired
//	AdminInterceptor adminInterceptor;

//	@Autowired
//	UserInterceptor userInterceptor;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 设置允许跨域的路径
		registry.addMapping("/**")
				// 设置允许跨域请求的域名
				.allowedOrigins("*")
				// 是否允许证书 不再默认开启
				.allowCredentials(false)
				// 设置允许的方法
				.allowedMethods("*")
				// 跨域允许时间
				.maxAge(3600);
	}

	//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(adminInterceptor);
////				.addPathPatterns("");
//		registry.addInterceptor(userInterceptor);
////				.addPathPatterns("");
//	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//和页面有关的静态目录都放在项目的static目录下
		registry.addResourceHandler("/static/imgs/**")
				.addResourceLocations("classpath:/static/imgs/");
		//上传的图片在D盘下的TA目录下，访问路径如：http://localhost:8081/OTA/d3cf0281-bb7f-40e0-ab77-406db95ccf2c.jpg
		//其中OTA表示访问的前缀。"file:D:/OTA/"是文件真实的存储路径
//		registry.addResourceHandler("/OTA/**").addResourceLocations("file:D:/OTA/");
	}

}
