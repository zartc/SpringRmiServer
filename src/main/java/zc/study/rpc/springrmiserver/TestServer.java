package zc.study.rpc.springrmiserver;

import org.springframework.boot.builder.SpringApplicationBuilder;

import zc.study.rpc.springrmiserver.server.ServerConfig;


/**
 * The main class that launch the server.
 */
public class TestServer {
	public static void main(String args[]) {
		new SpringApplicationBuilder(ServerConfig.class).web(false).run(args);
	}
}
