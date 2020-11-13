package ru.mail;

import filters.PostFilter;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import servlet.AddingFormServlet;
import servlet.ContentGenerator;
import servlet.GetProductServlet;
import servlet.ProductAddingServlet;

import javax.servlet.DispatcherType;
import java.net.URL;
import java.util.EnumSet;

public class Main {

	public static void main(String[] args) throws Exception{
		Server server = new CustomServer().build();

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
		context.setContextPath("/");

		final URL resource = LoginService.class.getResource("/static");
		context.setBaseResource(Resource.newResource(resource.toExternalForm()));
		context.setWelcomeFiles(new String[]{"/static/example"});

		context.addServlet(new ServletHolder("default", DefaultServlet.class),"/");
		context.addServlet(new ServletHolder("adding",new AddingFormServlet(new ContentGenerator())),"/add");
		context.addServlet(new ServletHolder("addingProduct", ProductAddingServlet.class),"/addProduct");
		context.addServlet(new ServletHolder("returnProducts", GetProductServlet.class),"/getProducts");

		final PostFilter filter = new PostFilter();
		final FilterHolder holder = new FilterHolder(filter);
		context.addFilter(holder,"/addProduct", EnumSet.of(DispatcherType.REQUEST));

		final String hashConfig = Main.class.getResource("/hash_config").toExternalForm();
		final HashLoginService hashLoginService = new HashLoginService("login",hashConfig);
		final ConstraintSecurityHandler security = new SecurityHandlerBuilder().build(hashLoginService);

		server.addBean(hashLoginService);
		security.setHandler(context);
		server.setHandler(security);

		server.start();
	}
}
