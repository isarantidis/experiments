package org.semmellitis.chesar;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(value = "org.semmellitis.chesar.persistence",
    entityManagerFactoryRef = "emf", transactionManagerRef = "tx")
@EnableTransactionManagement
public class Persistence {

  @Value("${database.vendor}")
  private String databaseVendor;

  @Value("${database.name}")
  private String databaseName;

  @Value("${database.pool}")
  private String poolType;

  @Bean
  public DataSource dataSource() {
    DataSource ds = null;
    if ("derby".equals(databaseVendor)) {
      EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
      ds = builder.setType(EmbeddedDatabaseType.DERBY).setName(databaseName).build();
    } else if ("h2".equals(databaseVendor)) {
      EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
      ds = builder.setType(EmbeddedDatabaseType.H2).setName(databaseName).build();
    } else if ("hsql".equals(databaseVendor)) {
      EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
      ds = builder.setType(EmbeddedDatabaseType.HSQL).setName(databaseName).build();
    } else {
      throw new IllegalArgumentException(databaseVendor + " is not a valid database vendor value");
    }
    ds = poolDataSource(ds);
    return ds;
  }

  private DataSource poolDataSource(DataSource ds) {
    HikariDataSource hds = new HikariDataSource();
    hds.setMaximumPoolSize(100);
    hds.setDataSource(ds);
    return hds;
  }

  @Bean(name = "emf")
  public EntityManagerFactory entityManagerFactory() {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setShowSql(true);
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("org.semmellitis.chesar.domain");
    factory.setDataSource(dataSource());
    factory.afterPropertiesSet();

    return factory.getObject();
  }

  @Bean(name = "tx")
  public PlatformTransactionManager transactionManager() {

    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    return txManager;
  }
}
