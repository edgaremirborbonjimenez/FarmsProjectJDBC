package org.example.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Animal;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.connection.HikariCPImplementation;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AnimalsIDAO implements IDAO<Animal> {
    private static final Logger logger = LogManager.getLogger();
    private IConnection<Connection> poolConnection;
    private static AnimalsIDAO animalsIDAO;
    private static final String  TABLE = "animals";

    private AnimalsIDAO(){}

    public static AnimalsIDAO getInstance(){
        if(animalsIDAO == null){
            animalsIDAO = new AnimalsIDAO();
        }
        return animalsIDAO;
    }

    @Override
    public Animal insert(Animal data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "INSERT INTO "+TABLE+" (name) value (?)";
        ResultSet res = null;
        try(PreparedStatement statement =  connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);){
            statement.setString(1,data.getName());
            statement.executeUpdate();
            res = statement.getGeneratedKeys();
            if(res.next()){
                Integer id = res.getInt(Statement.RETURN_GENERATED_KEYS);
                data.setId(id);
                return data;
            }
            logger.error("Occurred an error Animal was not returned");
            throw new Exception("Occurred an error Animal was not returned");
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
    public int updateById(int id, Animal data) throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "UPDATE "+TABLE+" SET ";
        if( data.getName() != null && !data.getName().isEmpty()){
            query+= "name = ?";
        }
        query+=" WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            int paramIndex = 0;
            if( data.getName() != null && !data.getName().isEmpty()){
                statement.setString(paramIndex,data.getName());
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
    public List<Animal> findAll()throws Exception {
        Connection connection = poolConnection.getConnectionFromPool();
        String query = "SELECT * from "+TABLE;
        ResultSet res = null;
        List<Animal> animalList = new LinkedList<>();
        try(PreparedStatement statement = connection.prepareStatement(query)){
            res = statement.executeQuery();
            while (res.next()){
                Integer id = res.getInt("id");
                String name = res.getString("name");
                Animal animal = new Animal();
                animal.setId(id);
                animal.setName(name);
                animalList.add(animal);
            }
            return animalList;
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
    public Animal findById(int id)throws Exception {
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
                Animal animal = new Animal();
                animal.setId(id);
                animal.setName(name);
                return animal;
            }
            logger.error("An error occurred, Animal was not retrieved");
            throw new Exception("An error occurred, Animal was not retrieved");
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
