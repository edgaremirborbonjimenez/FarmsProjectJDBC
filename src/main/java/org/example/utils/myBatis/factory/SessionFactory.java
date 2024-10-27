package org.example.utils.myBatis.factory;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utils.myBatis.mappers.GenericMapper;

import java.io.IOException;
import java.util.Properties;

public class SessionFactory<T>{
    private static final Logger logger = LogManager.getLogger();
    static Properties properties;
    Class<T> mapper;

    public SessionFactory(Class<T> mapper){
        properties = new Properties();
        this.mapper = mapper;
    }

    public SqlSessionFactory getSqlSessionFactory(){
        try{
            properties.load(SessionFactory.class.getClassLoader().getResourceAsStream("env.properties"));
        }catch (IOException e){
            logger.error(e.getMessage());
        }

        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(properties.getProperty("db.driver"));
        dataSource.setUrl(properties.getProperty("db.url"));
        dataSource.setUsername(properties.getProperty("db.username"));
        dataSource.setPassword(properties.getProperty("db.password"));

        Environment environment = new Environment("development",new JdbcTransactionFactory(),dataSource);

        Configuration configuration = new Configuration(environment);
        configuration.addMapper(mapper);

        return new SqlSessionFactoryBuilder().build(configuration);

    }

}
