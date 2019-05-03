package com.chasegarsee.projectgdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectgdpApplication
{

    public static CountryList countryList;

    public static void main(String[] args)
    {
        countryList = new CountryList();
        SpringApplication.run(ProjectgdpApplication.class, args);
    }

}
