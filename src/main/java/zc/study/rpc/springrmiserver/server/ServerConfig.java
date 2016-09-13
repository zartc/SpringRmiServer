package zc.study.rpc.springrmiserver.server;

import java.rmi.registry.Registry;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import zc.study.rpc.springrmiserver.RmiRegistryProperties;
import zc.study.rpc.springrmiserver.service.GreetingService;
import zc.study.rpc.springrmiserver.service.GreetingServiceImpl;


/**
 * The main class that instanciate the service classes, bind them into the RMI registry
 * and start the Server.
 */
@SpringBootApplication
//@EnableConfigurationProperties
@PropertySource("classpath:/rmi-registry.properties")
public class ServerConfig {

	@Bean
	RmiServiceExporter greetingServiceExporter(Registry registry, RmiRegistryProperties rmiRegistryProperties) throws Exception {
		RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
		rmiServiceExporter.setRegistry(registry);
//		rmiServiceExporter.setRegistryPort(rmiServerPropertie().getPort());
		rmiServiceExporter.setService(greetingService());
//		rmiServiceExporter.setServiceInterface(GreetingService.class);
		rmiServiceExporter.setServiceName(rmiRegistryProperties.getGreetingServiceName());

		return rmiServiceExporter;
	}

	@Bean
	RmiRegistryProperties rmiRegistryProperties() {
		return new RmiRegistryProperties();
	}

	@Bean
	public RmiRegistryFactoryBean rmiRegistry(RmiRegistryProperties rmiRegistryProperties) throws Exception {
		RmiRegistryFactoryBean rmiRegistryFactoryBean = new RmiRegistryFactoryBean();
		// if host is set it will always try to connect to that host and fail
		// if host is not set it will attempt to create a rmiRegistry on the current host.
//		rmiRegistryFactoryBean.setHost(rmiServerPropertie().getHost());
		rmiRegistryFactoryBean.setPort(rmiRegistryProperties.getPort());
		rmiRegistryFactoryBean.setAlwaysCreate(true);

		return rmiRegistryFactoryBean;
	}

	@Bean
	GreetingService greetingService() {
		return new GreetingServiceImpl();
	}
}
