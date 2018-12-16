package com.tajv.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mysql.fabric.xmlrpc.Client;
import com.tajv.dao.ClientDao;
import com.tajv.dao.ClientDaoImpl;
import com.tajv.dao.EmployeeDao;
import com.tajv.dao.EmployeeDaoImpl;
import com.tajv.dao.ItemDao;
import com.tajv.dao.ItemDaoImpl;
import com.tajv.dao.OrderDao;
import com.tajv.dao.OrderDaoImpl;
import com.tajv.dao.PrescriptionDao;
import com.tajv.dao.PrescriptionDaoImpl;
import com.tajv.dao.SaleDao;
import com.tajv.dao.SaleDaoImpl;
import com.tajv.model.Employee;
import com.tajv.model.Item;
import com.tajv.model.Order;
import com.tajv.model.Prescription;
import com.tajv.model.Sale;

@EnableWebMvc
@Configuration
@ComponentScan("com.tajv")
@EnableTransactionManagement
public class ApplicationContextConfig extends WebMvcConfigurerAdapter {

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSizePerFile(20971520); // 20 MB
		return resolver;
	}

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/**
	 * Connection to database
	 * 
	 * @return
	 * @throws IOException
	 */

	@Bean(name = "dataSource")
	public DataSource getDataSource() throws IOException {
		Properties properties = new Properties();
		String propertiesName = "persistence.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesName);
		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propertiesName + "' not found in the classpath");
		}
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(properties.getProperty("db.driver"));
		dataSource.setUrl(properties.getProperty("db.url"));
		dataSource.setUsername(properties.getProperty("db.username"));
		dataSource.setPassword(properties.getProperty("db.password"));

		return dataSource;
	}

	/**
	 * Hibernate properties
	 * 
	 * @return
	 */

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClasses(Item.class, Employee.class, Sale.class, Client.class, Order.class,
				Prescription.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "itemDao")
	public ItemDao getItemDao() {
		return new ItemDaoImpl();
	}

	@Autowired
	@Bean(name = "employeeDao")
	public EmployeeDao getEmployeeDao() {
		return new EmployeeDaoImpl();
	}

	@Autowired
	@Bean(name = "saleDao")
	public SaleDao getSaleDao() {
		return new SaleDaoImpl();
	}

	@Autowired
	@Bean(name = "clientDao")
	public ClientDao getClientDao() {
		return new ClientDaoImpl();
	}

	@Autowired
	@Bean(name = "orderDao")
	public OrderDao getOrderDao() {
		return new OrderDaoImpl();
	}

	@Autowired
	@Bean(name = "prescriptionDao")
	public PrescriptionDao getPrescriptionDao() {
		return new PrescriptionDaoImpl();
	}
}