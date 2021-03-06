package com.mxgraph.examples.web;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import com.mxgraph.examples.subscribe.singleSubscribe;

/**
 * The save servlet is used to echo XML to the client, eg. for SVG export and saving
 * (see Dialogs.js:SaveDialog and ExportDialog). The export servlet is used to
 * implement image and PDF export (see Dialogs.js:ExportDialog). Note that the
 * CSS support is limited to the following for all HTML markup:
 * http://docs.oracle.com/javase/6/docs/api/index.html?javax/swing/text/html/CSS.html
 * The open servlet is used to open files. It does this by calling some JavaScript
 * hook in the client-side page (see open.html).
 */
public class GraphEditor
{

	public static int PORT = 8080;

	/**
	 * Point your browser to http://localhost:8080/sparkPlatform/www/index.html
	 */
	public static void main(String[] args) throws Exception
	{
		Server server = new org.mortbay.jetty.Server(PORT);

		// Servlets
		Context context = new Context(server, "/");
		context.addServlet(new ServletHolder(new SaveServlet()), "/save");
		context.addServlet(new ServletHolder(new ExportServlet()), "/export");
		context.addServlet(new ServletHolder(new OpenServlet()), "/open");

		ResourceHandler fileHandler = new ResourceHandler();
		fileHandler.setResourceBase(".");

		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { fileHandler, context });
		server.setHandler(handlers);

		System.out.println("Go to http://localhost:8080/sparkPlatform/index.html");
		
		server.start();
		server.join();
		
	}
}
