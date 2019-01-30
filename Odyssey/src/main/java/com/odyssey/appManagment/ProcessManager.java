package com.odyssey.appManagment;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ProcessManager implements ServletContextListener {
    final static Logger logger = Logger.getLogger("converter");

    public void contextInitialized(ServletContextEvent sce) {
        // Startup tasks go here
    }


    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

}