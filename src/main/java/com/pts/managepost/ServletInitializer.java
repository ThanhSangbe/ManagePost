package com.pts.managepost;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.pts.managepost.Formatter.CategoryFormatter;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ManagePostApplication.class);
	}
	@Configuration
	static class myConfig extends WebMvcConfigurerAdapter
	{
		public void addFormatters(FormatterRegistry registry)
	    {
			registry.addFormatter(new CategoryFormatter());
	    }
	    
	}

}
