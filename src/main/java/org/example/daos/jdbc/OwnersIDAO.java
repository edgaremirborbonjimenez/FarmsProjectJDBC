package org.example.daos.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Owner;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class OwnersIDAO implements IDAO<Owner> {
    private static final Logger logger = LogManager.getLogger();
    private IConnection<Connection> poolConnection;
    private static OwnersIDAO ownersIDAO;
    private static final String  TABLE = "owners";

    private OwnersIDAO(){}

    public static OwnersIDAO getInstance(){
        if(ownersIDAO == null){
            ownersIDAO = new OwnersIDAO();
        }
        return ownersIDAO;
    }

    @Override
    public Owner insert(Owner data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "INSERT INTO "+TABLE+" (full_name,phone,email) value (?,?,?)";
        ResultSet res = null;
        try(PreparedStatement statement =  connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);){
            statement.setString(1,data.getFullName());
            statement.setString(2,data.getPhone());
            statement.setString(3,data.getEmail());
            statement.executeUpdate();
            res = statement.getGeneratedKeys();
            if(res.next()){
                Integer id = res.getInt(Statement.RETURN_GENERATED_KEYS);
                data.setId(id);
                return data;
            }
            logger.error("Occurred an error Owner was not returned");
            throw new Exception("Occurred an error Owner was not returned");
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
    public int updateById(int id, Owner data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "UPDATE "+TABLE+" SET ";
        boolean firstParam = true;
        if(data.getFullName() != null && !data.getFullName().isEmpty()){
            query+= "full_name = ?";
            firstParam = false;
        }
        if(data.getPhone() !=null && !data.getPhone().isEmpty()){
            if(!firstParam){
                query+=",";
            }
            query += "phone = ?";
            firstParam = false;
        }
        if(data.getEmail() != null && !data.getEmail().isEmpty()){
            if(!firstParam){
                query+=",";
            }
            query += "owner_id = ?";
            firstParam = false;
        }
        query+=" WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            int paramIndex = 0;
            if(data.getFullName() != null && !data.getFullName().isEmpty()){
                paramIndex++;
                statement.setString(paramIndex,data.getFullName());
            }
            if(data.getPhone() != null && !data.getPhone().isEmpty()){
                paramIndex++;
                statement.setString(paramIndex,data.getPhone());
            }
            if(data.getEmail() != null && !data.getEmail().isEmpty()){
                paramIndex++;
                statement.setString(paramIndex,data.getEmail());
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
        }     }

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
    public List<Owner> findAll() throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "SELECT * FROM "+TABLE;
        ResultSet res = null;
        List<Owner> ownerList = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement(query)){
            res = statement.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String fullName = res.getString("full_name");
                String phone = res.getString("phone");
                String email = res.getString("email");
                Owner owner = new Owner();
                owner.setId(id);
                owner.setFullName(fullName);
                owner.setPhone(phone);
                owner.setEmail(email);
                ownerList.add(owner);
            }
            return ownerList;
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
    public Owner findById(int id) throws Exception {
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
                String fullName = res.getString("full_name");
                String phone = res.getString("phone");
                String email = res.getString("email");
                Owner owner = new Owner();
                owner.setId(id);
                owner.setFullName(fullName);
                owner.setPhone(phone);
                owner.setEmail(email);
                return owner;
            }
            logger.error("An error occurred, Owner was not retrieved");
            throw new Exception("An error occurred, Owner was not retrieved");
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
