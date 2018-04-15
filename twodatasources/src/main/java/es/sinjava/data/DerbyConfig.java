package es.sinjava.data;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryDerby", transactionManagerRef = "transactionManagerDerby", basePackages = {
		"es.sinjava.data.repository.app" } )
@EnableTransactionManagement 
public class DerbyConfig {

	private static final Logger LOG = LoggerFactory.getLogger(DerbyConfig.class);

	@Bean(name = "dataSourceDerby")
	public DataSource dataSourceDerby() {
		BasicDataSource dataSource = new BasicDataSource();
		LOG.trace("DDBB username :" + "jpacxf");
		dataSource.setUsername("demotest");
		dataSource.setPassword("demotest");
		dataSource.setUrl("jdbc:derby://localhost:1527/demotest;create=true");
		return dataSource;
	}

	@Bean(name = "entityManagerDerby")
	public EntityManager entityManager() {
		return entityManagerFactoryDerby().createEntityManager();
	}

	// Este sigue apuntando a derby
	@Bean(name = "entityManagerFactoryDerby")
	public EntityManagerFactory entityManagerFactoryDerby() {
		LOG.trace("Init");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setDatabase(Database.DERBY);
		// Si está en debug muestra el SQL
		// vendorAdapter.setShowSql(LOG.isDebugEnabled());
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("es.sinjava.data.jpa.app.domain");
		factory.setDataSource(dataSourceDerby());
		factory.setPersistenceUnitName("usuarios");
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		jpaProperties.put("hibernate.hbm2ddl.import_files", "importusers.sql");
		factory.setJpaProperties(jpaProperties);
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean(name = "transactionManagerDerby")
	public PlatformTransactionManager transactionManagerDerby() throws SQLException {
		LOG.trace("Init");
		return new JpaTransactionManager(entityManagerFactoryDerby());
	}

}
