package org.example.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Animal;
import org.example.domain.Farm;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class FarmsIDAO implements IDAO<Farm> {
    private static final Logger logger = LogManager.getLogger();
    private IConnection<Connection> poolConnection;
    private static FarmsIDAO farmsIDAO;
    private static final String  TABLE = "farms";

    private FarmsIDAO(){}

    public static FarmsIDAO getInstance(){
        if(farmsIDAO == null){
            farmsIDAO = new FarmsIDAO();
        }
        return  farmsIDAO;
    }

    @Override
    public Farm insert(Farm data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "INSERT INTO "+TABLE+" (name,address,owner_id) value (?,?,?)";
        ResultSet res = null;
        try(PreparedStatement statement =  connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);){
            statement.setString(1,data.getName());
            statement.setString(2,data.getAddress());
            statement.setInt(3,data.getOwner_id());
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
    public int updateById(int id, Farm data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "UPDATE "+TABLE+" SET ";
        boolean firstParam = true;
        if( data.getName() != null && !data.getName().isEmpty()){
            query+= "name = ?";
            firstParam = false;
        }
        if(data.getAddress() != null && !data.getAddress().isEmpty()){
            if(!firstParam){
                query+=",";
            }
            query += "address = ?";
            firstParam = false;
        }
        if(data.getOwner_id() > 0){
            if(!firstParam){
                query+=",";
            }
            query += "owner_id = ?";
            firstParam = false;
        }
        query+=" WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            int paramIndex = 0;
            if(data.getName() != null && !data.getName().isEmpty()){
                paramIndex++;
                statement.setString(paramIndex,data.getName());
            }
            if(data.getAddress() != null && !data.getAddress().isEmpty()){
                paramIndex++;
                statement.setString(paramIndex,data.getAddress());
            }
            if(data.getOwner_id() > 0){
                paramIndex++;
                statement.setInt(paramIndex,data.getOwner_id());
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
    public List<Farm> findAll() throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "SELECT * FROM "+TABLE;
        ResultSet res = null;
        List<Farm> farmList = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement(query)){
            res = statement.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                String address = res.getString("address");
                Integer owner_id = res.getInt("owner_id");
                Farm farm = new Farm();
                farm.setId(id);
                farm.setName(name);
                farm.setAddress(address);
                farm.setOwner_id(owner_id);
                farmList.add(farm);
            }
            return farmList;
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
    public Farm findById(int id) throws Exception {
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
                String address = res.getString("address");
                Integer owner_id = res.getInt("owner_id");
                Farm farm = new Farm();
                farm.setId(id);
                farm.setName(name);
                farm.setAddress(address);
                farm.setOwner_id(owner_id);
                return farm;
            }
            logger.error("An error occurred, Farm was not retrieved");
            throw new Exception("An error occurred, Farm was not retrieved");
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
