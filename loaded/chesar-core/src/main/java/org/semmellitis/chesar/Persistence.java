package org.semmellitis.chesar;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(value = "org.semmellitis.chesar.persistence",
    entityManagerFactoryRef = "emf", transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class Persistence {

  @Value("${database.vendor}")
  private String databaseVendor;

  @Value("${database.name}")
  private String databaseName;

  @Value("${database.pool}")
  private String poolType;

  @Value("${database.inmemoryDb}")
  private boolean inmemoryDb;

  private DataSource database;

  @Bean
  public DataSource dataSource() {
    DataSource ds = null;
    if (inmemoryDb) {
      ds = createInMemoryDataSource();
    } else {
      ds = createDataSource();
    }
    ds = poolDataSource(ds);
    database = ds;
    return database;
  }

  private DataSource createDataSource() {
    // TODO Auto-generated method stub
    return null;
  }

  private DataSource createInMemoryDataSource() {
    if ("derby".equals(databaseVendor)) {
      EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
      return builder.setType(EmbeddedDatabaseType.DERBY).setName(databaseName).build();
    } else if ("h2".equals(databaseVendor)) {
      EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
      return builder.setType(EmbeddedDatabaseType.H2).setName(databaseName).build();
    } else if ("hsql".equals(databaseVendor)) {
      EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
      return builder.setType(EmbeddedDatabaseType.HSQL).setName(databaseName).build();
    } else {
      throw new IllegalArgumentException(databaseVendor + " is not a valid database vendor value");
    }
  }

  private DataSource poolDataSource(DataSource ds) {
    HikariDataSource hds = new HikariDataSource();
    hds.setMaximumPoolSize(100);
    hds.setDataSource(ds);
    return hds;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter(DataSource dataSource) {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setShowSql(true);
    vendorAdapter.setGenerateDdl(true);

    return vendorAdapter;
  }

  @Bean(name = "emf")
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource,
      JpaVendorAdapter vendorAdapter) {
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("org.semmellitis.chesar.domain");
    factory.setDataSource(dataSource);
    factory.afterPropertiesSet();

    return factory;
  }


  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory);
    return txManager;
  }

  @PreDestroy
  public void shutdownDatabase() {
    ((HikariDataSource) this.database).shutdown();
  }
}
