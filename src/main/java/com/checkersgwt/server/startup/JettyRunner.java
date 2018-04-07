package com.checkersgwt.server.startup;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ErrorPageErrorHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle;
import org.eclipse.jetty.util.component.LifeCycle;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class JettyRunner implements InitializingBean {

    private Server server;
    private int threadPoolSize = 50;
    private int httpPort;
    private String springContext;
    private File staticDir;

    @Override
    public void afterPropertiesSet() throws Exception {
        server = new Server(new QueuedThreadPool(threadPoolSize));

        ServerConnector serverConnector = new ServerConnector(server);
        serverConnector.setPort(httpPort);
        server.setConnectors(new Connector[]{serverConnector});

        //add spring-context.xml
        final XmlWebApplicationContext ctx = new XmlWebApplicationContext();
        ctx.setConfigLocation(springContext);

        final WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setResourceBase("./" + staticDir.getName());
//        webAppContext.setParentLoaderPriority(true);
        webAppContext.addEventListener(new ContextLoaderListener(ctx));
    
        ErrorPageErrorHandler errorHandler = new ErrorPageErrorHandler();
        errorHandler.addErrorPage(404, "/error404");
        webAppContext.setErrorHandler(errorHandler);
        
        server.setHandler(webAppContext);

        //Add Dispatcher (sort of instead of using web.xml)
        server.addLifeCycleListener(new AbstractLifeCycle.AbstractLifeCycleListener() {

            @Override
            public void lifeCycleStarted(LifeCycle event) {
                DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
                ServletHolder servletHolder = new ServletHolder();
                servletHolder.setName("dispatcherServlet");
                servletHolder.setServlet(dispatcherServlet);
                servletHolder.setInitOrder(1);
                webAppContext.addServlet(servletHolder, "/");
                try {
                    servletHolder.start();
                } catch (Exception e) {}
            }
        });

        server.start();
    }

    @Required
    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    @Required
    public void setSpringContext(String springContext) {
        this.springContext = springContext;
    }

    @Required
    public void setStaticDir(File staticDir) {
        this.staticDir = staticDir;
    }
}
