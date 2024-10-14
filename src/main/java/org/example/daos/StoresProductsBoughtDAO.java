package org.example.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.FarmSupplyProductBought;
import org.example.domain.StoreProductBought;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class StoresProductsBoughtDAO implements IDAO<StoreProductBought> {
    private static final Logger logger = LogManager.getLogger();
    private IConnection<Connection> poolConnection;
    private static StoresProductsBoughtDAO storesProductsBoughtDAO;
    private static final String  TABLE = "stores_product_bought";

    private StoresProductsBoughtDAO(){}

    public static StoresProductsBoughtDAO getInstance(){
        if(storesProductsBoughtDAO == null){
            storesProductsBoughtDAO = new StoresProductsBoughtDAO();
        }
        return storesProductsBoughtDAO;
    }

    @Override
    public StoreProductBought insert(StoreProductBought data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "INSERT INTO "+TABLE+" (amount,total,product_id,store_id) value (?,?,?,?)";
        ResultSet res = null;
        try(PreparedStatement statement =  connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);){
            statement.setInt(1,data.getAmount());
            statement.setDouble(2,data.getTotal());
            statement.setInt(3,data.getProduct_id());
            statement.setInt(4,data.getStore_id());
            statement.executeUpdate();
            res = statement.getGeneratedKeys();
            if(res.next()){
                Integer id = res.getInt(Statement.RETURN_GENERATED_KEYS);
                data.setId(id);
                return data;
            }
            logger.error("Occurred an error StoreProductBought was not returned");
            throw new Exception("Occurred an error StoreProductBought was not returned");
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
    public int updateById(int id, StoreProductBought data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "UPDATE "+TABLE+" SET ";
        boolean firstParam = true;
        if( data.getAmount() != null && data.getAmount() > 0){
            query+= "amount = ?";
            firstParam = false;
        }
        if(data.getTotal() != null && data.getTotal() > 0){
            if(!firstParam){
                query+=",";
            }
            query += "total = ?";
            firstParam = false;
        }
        if(data.getProduct_id() != null && data.getProduct_id() > 0){
            if(!firstParam){
                query+=",";
            }
            query += "product_id = ?";
            firstParam = false;
        }
        if(data.getStore_id() != null &&data.getStore_id() > 0){
            if(!firstParam){
                query+=",";
            }
            query += "store_id = ?";
            firstParam = false;
        }
        query+=" WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            int paramIndex = 0;
            if(data.getAmount() != null && data.getAmount() > 0){
                paramIndex++;
                statement.setInt(paramIndex,data.getAmount());
            }
            if(data.getTotal() != null && data.getTotal() > 0){
                paramIndex++;
                statement.setDouble(paramIndex,data.getTotal());
            }
            if(data.getProduct_id() != null &&data.getProduct_id() > 0){
                paramIndex++;
                statement.setInt(paramIndex,data.getProduct_id());
            }
            if(data.getStore_id() != null &&data.getStore_id() > 0){
                paramIndex++;
                statement.setInt(paramIndex,data.getStore_id());
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
        }
    }

    @Override
    public List<StoreProductBought> findAll() throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "SELECT * FROM "+TABLE;
        ResultSet res = null;
        List<StoreProductBought> list = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement(query)){
            res = statement.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                Integer amount = res.getInt("amount");
                Double total = res.getDouble("total");
                Integer store_id = res.getInt("store_id");
                Integer product_id = res.getInt("product_id");
                StoreProductBought f = new StoreProductBought();
                f.setId(id);
                f.setAmount(amount);
                f.setTotal(total);
                f.setStore_id(store_id);
                f.setProduct_id(product_id);
                list.add(f);
            }
            return list;
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            if(res != null){
                res.close();
            }
            poolConnection.releaseConnection(connection);
        }
    }

    @Override
    public StoreProductBought findById(int id) throws Exception {
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
                Integer amount = res.getInt("amount");
                Double total = res.getDouble("total");
                Integer store_id = res.getInt("store_id");
                Integer product_id = res.getInt("product_id");
                StoreProductBought f = new StoreProductBought();
                f.setId(id);
                f.setAmount(amount);
                f.setTotal(total);
                f.setStore_id(store_id);
                f.setProduct_id(product_id);
                return f;
            }
            logger.error("An error occurred, StoreProductBought was not retrieved");
            throw new Exception("An error occurred, StoreProductBought was not retrieved");
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }finally {
            if(res != null){
                res.close();
            }
            poolConnection.releaseConnection(connection);
        }
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {
        this.poolConnection = poolConnection;
    }


}
