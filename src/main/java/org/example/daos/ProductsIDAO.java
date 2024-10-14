package org.example.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Farm;
import org.example.domain.Product;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.enums.UnitMeasurement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ProductsIDAO implements IDAO<Product> {
    private static final Logger logger = LogManager.getLogger();
    private IConnection<Connection> poolConnection;
    private static ProductsIDAO productsIDAO;
    private static final String  TABLE = "products";

    private ProductsIDAO(){}

    public static ProductsIDAO getInstance(){
        if(productsIDAO == null){
            productsIDAO = new ProductsIDAO();
        }
        return productsIDAO;
    }

    @Override
    public Product insert(Product data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "INSERT INTO "+TABLE+" (name,price,unit_measurement) value (?,?,?)";
        ResultSet res = null;
        try(PreparedStatement statement =  connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);){
            statement.setString(1,data.getName());
            statement.setDouble(2,data.getPrice());
            statement.setString(3,data.getUnitMeasurement());
            statement.executeUpdate();
            res = statement.getGeneratedKeys();
            if(res.next()){
                Integer id = res.getInt(Statement.RETURN_GENERATED_KEYS);
                data.setId(id);
                return data;
            }
            logger.error("Occurred an error Farm was not returned");
            throw new Exception("Occurred an error Farm was not returned");
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            if(res!=null){
                res.close();
            }
            poolConnection.releaseConnection(connection);
        }
    }

    @Override
    public int updateById(int id, Product data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "UPDATE "+TABLE+" SET ";
        boolean firstParam = true;
        if( data.getName() != null && !data.getName().isEmpty()){
            query+= "name = ?";
            firstParam = false;
        }
        if(data.getPrice() >= 0){
            if(!firstParam){
                query+=",";
            }
            query += "price = ?";
            firstParam = false;
        }
        if( data.getUnitMeasurement() != null && !data.getUnitMeasurement().isBlank()){
            if(!firstParam){
                query+=",";
            }
            query+= "unit_measurement = ?";
            firstParam = false;
        }
        query+=" WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            int paramIndex = 0;
            if(data.getName() != null && !data.getName().isEmpty()){
                paramIndex++;
                statement.setString(paramIndex,data.getName());
            }
            if(data.getPrice() >= 0){
                paramIndex++;
                statement.setDouble(paramIndex,data.getPrice());
            }
            if(data.getUnitMeasurement() != null && !data.getUnitMeasurement().isBlank()){
                paramIndex++;
                statement.setString(paramIndex,data.getUnitMeasurement());
            }
            paramIndex++;
            statement.setInt(paramIndex,id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected;
        }catch(Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            poolConnection.releaseConnection(connection);
        }
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        if(connection == null){
            return -1;
        }
        String query = "DELETE FROM "+TABLE+" WHERE id = ?";
        if(id == null){
            logger.warn("id is null");
            return -1;
        }
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,id);
            return statement.executeUpdate();
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            poolConnection.releaseConnection(connection);
        }    }

    @Override
    public List<Product> findAll() throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "SELECT * FROM "+TABLE;
        ResultSet res = null;
        List<Product> productList = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement(query)){
            res = statement.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                double price = res.getInt("price");
                String uniteMeasurement = res.getString("unit_measurement");
                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                product.setUnitMeasurement(uniteMeasurement);
                productList.add(product);
            }
            return productList;
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            if(res != null){
                res.close();
            }
            poolConnection.releaseConnection(connection);
        }    }

    @Override
    public Product findById(int id) throws Exception {
        if(id <=0 ){
            logger.warn("id: "+id+" not valid");
            return null;
        }
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "SELECT * from "+TABLE+" WHERE id = ?";
        ResultSet res = null;
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,id);
            res = statement.executeQuery();
            while (res.next()){
                String name = res.getString("name");
                double price = res.getInt("price");
                Product product = new Product();
                product.setId(id);
                product.setName(name);
                product.setPrice(price);
                return product;
            }
            logger.error("An error occurred, Products was not retrieved");
            throw new Exception("An error occurred, Products was not retrieved");
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            if(res != null){
                res.close();
            }
            poolConnection.releaseConnection(connection);
        }    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {
        this.poolConnection = poolConnection;
    }


}
