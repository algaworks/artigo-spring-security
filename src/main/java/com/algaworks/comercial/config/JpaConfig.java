package com.algaworks.comercial.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.algaworks.comercial.SpringWebInitializer;

@EnableJpaRepositories(basePackageClasses = SpringWebInitializer.class)
@EnableTransactionManagement
@Configuration
public class JpaConfig {
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		Properties properties = new Properties();		
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.hbm2ddl.auto", "create-drop");		
		properties.put("hibernate.hbm2ddl.import_files", "import.sql");		
		
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(jpaVendorAdapter);		
		bean.setPackagesToScan(SpringWebInitializer.class.getPackage().getName());		
		bean.setPersistenceUnitName("AlgaWorksSpringMVC");
		bean.setJpaProperties(properties);
		
		return bean;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
		
		bean.setDatabase(Database.H2);
		bean.setGenerateDdl(true);
		bean.setShowSql(true);
		
		return bean;
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.build();
	}
}