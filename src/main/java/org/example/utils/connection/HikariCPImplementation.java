package org.example.utils.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.ConnectionException;
import org.example.interfaces.IConnection;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class HikariCPImplementation implements IConnection<Connection> {
    private static final Logger logger = LogManager.getLogger();
    private int poolSize;
    private static HikariCPImplementation hikariImp;
    private static HikariDataSource dataSource;


    private HikariCPImplementation(){}

    public static HikariCPImplementation getInstance(){
        if(hikariImp == null){
            hikariImp = new HikariCPImplementation();
        }
        return hikariImp;
    }

    @Override
    public void startPool(){
        Properties properties = new Properties();
        try (FileReader input = new FileReader("src/main/resources/env.properties")){
            properties.load(input);
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setUsername(properties.getProperty("db.username"));
            config.setPassword(properties.getProperty("db.password"));
            config.setMaximumPoolSize(this.poolSize);
            dataSource = new HikariDataSource(config);
            logger.info("Pool Connection Created");
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    public void setPoolSize(int size){
        this.poolSize = size;
    }

    @Override
    public Connection getConnectionFromPool() throws ConnectionException{
        try{
            if(dataSource == null){
                startPool();
            }
            Connection con = dataSource.getConnection();
            logger.info("Connection gotten");
            return con;
        }catch (SQLException e){
            logger.error("Error: {}", e.getMessage());
            throw new ConnectionException("Error getting database connection");
        }
    }

    @Override
    public void releaseConnection(Connection connection) throws ConnectionException {
        try{
            if(connection != null){
                connection.close();
                logger.info("Connection released");
            }
        }catch (SQLException e){
            logger.error("Error: {}", e.getMessage());
            throw  new ConnectionException("Error closing database connection");
        }
    }


}
