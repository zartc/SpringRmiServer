package zc.study.rpc.springrmiserver;

import org.springframework.boot.builder.SpringApplicationBuilder;

import zc.study.rpc.springrmiserver.client.ClientConfig;


/**
 * The main class that launch the client.
 */
public class TestClient {
	public static void main(String args[]) {
		new SpringApplicationBuilder(ClientConfig.class).web(false).run(args);
	}
}
