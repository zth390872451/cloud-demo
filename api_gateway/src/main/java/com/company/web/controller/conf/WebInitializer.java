package com.company.web.controller.conf;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
public class WebInitializer implements ServletContextInitializer {

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
	//Druid Monitor
    ServletRegistration servlet = servletContext.addServlet("DruidStatView", StatViewServlet.class);
    servlet.addMapping("/druid/*");

  }
}
