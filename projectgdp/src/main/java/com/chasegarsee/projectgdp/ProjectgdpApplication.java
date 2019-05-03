package com.chasegarsee.projectgdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@SpringBootApplication
public class ProjectgdpApplication
{

    public static CountryList countryList;

    public static void main(String[] args)
    {
        countryList = new CountryList();
        SpringApplication.run(ProjectgdpApplication.class, args);

        ApplicationContext ctx = SpringApplication.run(ProjectgdpApplication.class, args);
        DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}
