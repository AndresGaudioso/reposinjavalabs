package es.sinjava.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Path;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.spring.JaxRsConfig;
import org.apache.cxf.jaxrs.spring.SpringResourceFactory;
import org.apache.cxf.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * 
 * @author Andrés Gaudioso mailto:andgau@gmail.com
 *
 */
@Configuration
@ComponentScan
@Import(JaxRsConfig.class)
public class BaseConfig {

	@Autowired
	ApplicationContext ctx;

	// Metemos el interceptor en el contexto por si hay que hacer algo complejo
	// y tal
	@Autowired
	private InInterceptor inInterceptor;

	private static final Logger LOG = LoggerFactory.getLogger(BaseConfig.class);

	@Bean
	public Server jaxRsServer() {
		LOG.trace("Init");
		LinkedList<ResourceProvider> resourceProviders = new LinkedList<>();
		for (String beanName : ctx.getBeanNamesForAnnotation(Path.class)) {
			SpringResourceFactory factory = new SpringResourceFactory(beanName);
			factory.setApplicationContext(ctx);
			resourceProviders.add(factory);
		}

		JAXRSServerFactoryBean jaxRSServerFactoryBean = new JAXRSServerFactoryBean();
		jaxRSServerFactoryBean.setBus(ctx.getBean(SpringBus.class));
		jaxRSServerFactoryBean.setProviders(Arrays.asList(new JacksonJsonProvider(), new CorsProvider()));

		jaxRSServerFactoryBean.setResourceProviders(resourceProviders);
		List<Interceptor<? extends Message>> inter = new ArrayList<>();
		inter.add(inInterceptor);
		jaxRSServerFactoryBean.setInInterceptors(inter);
		return jaxRSServerFactoryBean.create();
	}
}
