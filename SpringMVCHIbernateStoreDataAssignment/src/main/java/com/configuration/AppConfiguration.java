package com.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bean.User;



@Configuration
@PropertySource("classpath:databse.properties")
@EnableTransactionManagement
@ComponentScans(value = { 
	      @ComponentScan("com.dao"),
	      @ComponentScan("com.service")
	    })
public class AppConfiguration {

	@Autowired
	private Environment env;
	
	@Bean
    public DataSource getDataSource() {
		BasicDataSource datasource=new BasicDataSource();
		datasource.setDriverClassName(env.getProperty("data.driver"));
		datasource.setUrl(env.getProperty("data.url"));
		datasource.setUsername(env.getProperty("data.username"));
		datasource.setPassword(env.getProperty("data.password"));
		return datasource;
		
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionfactory() {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(getDataSource());
		
		Properties prop = new Properties();
		prop.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		prop.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		
		factory.setHibernateProperties(prop);
		factory.setAnnotatedClasses(User.class);
		
		return factory;
	}
	
	@Bean
	public HibernateTransactionManager getTransaction() {
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(getSessionfactory().getObject());
		
		return tx;
				
	}
	
}
