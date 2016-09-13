package zc.study.rpc.springrmiserver;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author Pascal
 * @see <a href=
 *      "http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config-typesafe-configuration-properties">Spring
 *      Boot Reference Guide §Type-safe Configuration Properties</a>
 * @see <a href=
 *      "http://www.baeldung.com/2012/02/06/properties-with-spring/?utm_source=email-newsletter&utm_medium=email&utm_campaign=auto_7_spring">Properties
 *      with Spring</a>
 */
@ConfigurationProperties(prefix = "rmi.registry")
public class RmiRegistryProperties {
	/**
	 * IP of the server
	 */
	private String host = "localhost";

	/**
	 * The RMI port number
	 */
	private int port = 1299;

	/**
	 * The name under which the greeting service is registered in the Rmi Registry.
	 */
	private String greetingServiceName = "zc.study.rpc.rmi.greetingService";


	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getGreetingServiceName() {
		return greetingServiceName;
	}

	public void setGreetingServiceName(String greetingServiceName) {
		this.greetingServiceName = greetingServiceName;
	}
}
