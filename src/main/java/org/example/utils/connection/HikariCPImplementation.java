package org.example.utils.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.ConnectionException;
import org.example.interfaces.IConnection;
import java.sql.Connection;
import java.sql.SQLException;

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
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost/farms");
        config.setUsername("root");
        config.setPassword("bone770115");
        config.setMaximumPoolSize(this.poolSize);
        dataSource = new HikariDataSource(config);
        logger.info("Pool Connection Created");
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
