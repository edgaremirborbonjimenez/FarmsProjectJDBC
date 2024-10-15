package org.example.daos.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.FarmSupplyProductInventory;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class FarmsSupplyProductsInventoryIDAO implements IDAO<FarmSupplyProductInventory> {
    private static final Logger logger = LogManager.getLogger();
    private IConnection<Connection> poolConnection;
    private static FarmsSupplyProductsInventoryIDAO farmsSupplyProductsInventoryIDAO;
    private static final String  TABLE = "farms_supply_product_inventory";

    private FarmsSupplyProductsInventoryIDAO(){}

    public static FarmsSupplyProductsInventoryIDAO getInstance(){
        if(farmsSupplyProductsInventoryIDAO == null){
            farmsSupplyProductsInventoryIDAO = new FarmsSupplyProductsInventoryIDAO();
        }
        return farmsSupplyProductsInventoryIDAO;
    }

    @Override
    public FarmSupplyProductInventory insert(FarmSupplyProductInventory data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "INSERT INTO "+TABLE+" (amount,farm_id,product_id) value (?,?,?)";
        ResultSet res = null;
        try(PreparedStatement statement =  connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);){
            statement.setInt(1,data.getAmount());
            statement.setInt(2,data.getFarm_id());
            statement.setInt(3,data.getProduct_id());
            statement.executeUpdate();
            res = statement.getGeneratedKeys();
            if(res.next()){
                Integer id = res.getInt(Statement.RETURN_GENERATED_KEYS);
                data.setId(id);
                return data;
            }
            logger.error("Occurred an error FarmSupplyProductInventory was not returned");
            throw new Exception("Occurred an error FarmSupplyProductInventory was not returned");
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
    public int updateById(int id, FarmSupplyProductInventory data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "UPDATE "+TABLE+" SET ";
        boolean firstParam = true;
        if( data.getAmount() != null && data.getAmount() > 0){
            query+= "amount = ?";
            firstParam = false;
        }
        if(data.getFarm_id() != null &&data.getFarm_id() > 0){
            if(!firstParam){
                query+=",";
            }
            query += "farm_id = ?";
            firstParam = false;
        }
        if(data.getProduct_id() != null && data.getProduct_id() > 0){
            if(!firstParam){
                query+=",";
            }
            query += "product_id = ?";
            firstParam = false;
        }
        query+=" WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            int paramIndex = 0;
            if(data.getAmount() != null){
                paramIndex++;
                statement.setInt(paramIndex,data.getAmount());
            }
            if(data.getFarm_id() != null &&data.getFarm_id() > 0){
                paramIndex++;
                statement.setInt(paramIndex,data.getFarm_id());
            }
            if(data.getProduct_id() != null &&data.getProduct_id() > 0){
                paramIndex++;
                statement.setInt(paramIndex,data.getProduct_id());
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
    public List<FarmSupplyProductInventory> findAll() throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "SELECT * FROM "+TABLE;
        ResultSet res = null;
        List<FarmSupplyProductInventory> list = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement(query)){
            res = statement.executeQuery();
            while (res.next()){
                Integer id = res.getInt("id");
                Integer amount = res.getInt("amount");
                Integer farm_id = res.getInt("farm_id");
                Integer product_id = res.getInt("product_id");
                FarmSupplyProductInventory f = new FarmSupplyProductInventory();
                f.setId(id);
                f.setAmount(amount);
                f.setFarm_id(farm_id);
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
    public FarmSupplyProductInventory findById(int id) throws Exception {
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
                Integer farm_id = res.getInt("farm_id");
                Integer product_id = res.getInt("product_id");
                FarmSupplyProductInventory f = new FarmSupplyProductInventory();
                f.setId(id);
                f.setAmount(amount);
                f.setFarm_id(farm_id);
                f.setProduct_id(product_id);
                return f;
            }
            logger.error("An error occurred, FarmSupplyProductBought was not retrieved");
            throw new Exception("An error occurred, FarmSupplyProductBought was not retrieved");
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
