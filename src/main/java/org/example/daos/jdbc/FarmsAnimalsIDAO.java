package org.example.daos.jdbc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.FarmAnimal;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class FarmsAnimalsIDAO implements IDAO<FarmAnimal> {
    private static final Logger logger = LogManager.getLogger();
    private IConnection<Connection> poolConnection;
    private static FarmsAnimalsIDAO farmsAnimalsIDAO;
    private static final String  TABLE = "farms_animals";

    private FarmsAnimalsIDAO(){}

    public static FarmsAnimalsIDAO getInstance(){
        if(farmsAnimalsIDAO == null){
            farmsAnimalsIDAO = new FarmsAnimalsIDAO();
        }
        return farmsAnimalsIDAO;
    }
    @Override
    public FarmAnimal insert(FarmAnimal data)throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "INSERT INTO "+TABLE+" (amount,farm_id,animal_id) value (?,?,?)";
        ResultSet res = null;
        try(PreparedStatement statement =  connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);){
            statement.setInt(1,data.getAmount());
            statement.setInt(2,data.getFarm_id());
            statement.setInt(3,data.getAnimal_id());
            statement.executeUpdate();
            res = statement.getGeneratedKeys();
            if(res.next()){
                Integer id = res.getInt(Statement.RETURN_GENERATED_KEYS);
                data.setId(id);
                return data;
            }
            logger.error("Occurred an error FarmAnimal was not returned");
            throw new Exception("Occurred an error FarmAnimal was not returned");
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
    public int updateById(int id, FarmAnimal data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "UPDATE "+TABLE+" SET ";
        boolean firstParam = true;

        if(data.getAmount() != null){
            if(!firstParam){
                query+=",";
            }
            query += "amount = ?";
            firstParam = false;
        }
        if(data.getFarm_id() != null && data.getFarm_id() > 0){
            if(!firstParam){
                query+=",";
            }
            query += "farm_id = ?";
            firstParam = false;
        }
        if(data.getAnimal_id() != null && data.getAnimal_id() > 0){
            if(!firstParam){
                query+=",";
            }
            query += "animal_id = ?";
            firstParam = false;
        }
        query+=" WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            int paramIndex = 0;
            if(data.getAmount() != null){
                paramIndex++;
                statement.setInt(paramIndex,data.getAmount());
            }
            if(data.getFarm_id() != null && data.getFarm_id() > 0){
                paramIndex++;
                statement.setInt(paramIndex,data.getFarm_id());
            }
            if(data.getAnimal_id() != null && data.getAnimal_id() > 0){
                paramIndex++;
                statement.setInt(paramIndex,data.getAnimal_id());
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
    public List<FarmAnimal> findAll() throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "SELECT * FROM "+TABLE;
        ResultSet res = null;
        List<FarmAnimal> farmAnimalList = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement(query)){
            res = statement.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                int amount = res.getInt("amount");
                int farmId = res.getInt("farm_id");
                int animalId = res.getInt("animal_id");
                FarmAnimal farmAnimal = new FarmAnimal();
                farmAnimal.setId(id);
                farmAnimal.setAmount(amount);
                farmAnimal.setFarm_id(farmId);
                farmAnimal.setAnimal_id(animalId);
                farmAnimalList.add(farmAnimal);
            }
            return farmAnimalList;
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
    public FarmAnimal findById(int id) throws Exception {
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
                int amount = res.getInt("amount");
                int farmId = res.getInt("farm_id");
                int animalId = res.getInt("animal_id");
                FarmAnimal farmAnimal = new FarmAnimal();
                farmAnimal.setId(id);
                farmAnimal.setAmount(amount);
                farmAnimal.setFarm_id(farmId);
                farmAnimal.setAnimal_id(animalId);
                return farmAnimal;
            }
            logger.error("An error occurred, FarmAnimal was not retrieved");
            throw new Exception("An error occurred, FarmAnimal was not retrieved");
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
