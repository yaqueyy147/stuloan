package com.base.jetty;

import cq.hlideal.jetty.main.server.JettyWebServer;

public class JettyTest {
	//实际部署时删除
	public static int port = 8022;
	public static String host = "127.0.0.1";
	public static String tempdir = "d://temp13";
	public static String logdir = "d://temp13";
	public static String webdir = "src/main/webapp/";
	public static String contextpath = "";
	
	public static void main(String[] args) throws Exception{		
		JettyWebServer server = new JettyWebServer(
				port,
                host,
                tempdir,
                webdir,
                logdir,
                contextpath);		
		server.start();
        server.join();
	}
}
