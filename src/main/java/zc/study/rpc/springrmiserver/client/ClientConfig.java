package zc.study.rpc.springrmiserver.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

import zc.study.rpc.springrmiserver.RmiRegistryProperties;
import zc.study.rpc.springrmiserver.service.GreetingService;


@SpringBootApplication
@PropertySource("classpath:/rmi-registry.properties")
public class ClientConfig implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(ClientConfig.class);

	@Inject
	GreetingService greetingService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("GreetingService found : {}", greetingService);

		String message = greetingService.computeGreetingMessage("Pascal JACOB");
		log.info("Server says: {}", message);
	}

	@Bean
	RmiRegistryProperties rmiRegistryProperties() {
		return new RmiRegistryProperties();
	}

	@Bean
	public RmiRegistryFactoryBean rmiRegistry(RmiRegistryProperties rmiRegistryProperties) throws Exception {
		RmiRegistryFactoryBean rmiRegistryFactoryBean = new RmiRegistryFactoryBean();
		// if host is set it will always try to connect to that host and fail if not RMIRegistry exist on that host
		// if host is not set it will attempt to create a rmiRegistry on the current host.
		rmiRegistryFactoryBean.setHost(rmiRegistryProperties.getHost());
		rmiRegistryFactoryBean.setPort(rmiRegistryProperties.getPort());
		rmiRegistryFactoryBean.setAlwaysCreate(true);

		return rmiRegistryFactoryBean;
	}

	@Bean
	GreetingService greetingService(Registry registry, RmiRegistryProperties rmiRegistryProperties)
			throws RemoteException, NotBoundException {
		return (GreetingService)registry.lookup(rmiRegistryProperties.getGreetingServiceName());
	}
}
