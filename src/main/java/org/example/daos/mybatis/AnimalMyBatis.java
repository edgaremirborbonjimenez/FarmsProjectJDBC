package org.example.daos.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Animal;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.myBatis.factory.SessionFactory;
import org.example.utils.myBatis.mappers.AnimalMapper;

import java.sql.Connection;
import java.util.List;

public class AnimalMyBatis implements IDAO<Animal> {
    private static final Logger logger = LogManager.getLogger();
    SqlSessionFactory sqlSessionFactory;

    public AnimalMyBatis(){
        SessionFactory<AnimalMapper> sessionFactory = new SessionFactory<>(AnimalMapper.class);
        this.sqlSessionFactory = sessionFactory.getSqlSessionFactory();

    }

    @Override
    public Animal insert(Animal data) throws Exception {
        //Can allow auto-commit setting "true" into the openSession method
        try(SqlSession session = sqlSessionFactory.openSession()){
            AnimalMapper animalMapper = session.getMapper(AnimalMapper.class);
            Integer affected = animalMapper.insert(data);
            session.commit();
            if(affected==0){
                return null;
            }
            return animalMapper.findById(data.getId());

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int updateById(int id, Animal data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            AnimalMapper animalMapper = session.getMapper(AnimalMapper.class);
            return animalMapper.updateById(id,data);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            AnimalMapper animalMapper = session.getMapper(AnimalMapper.class);
            return animalMapper.deleteById(id);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Animal> findAll() throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            AnimalMapper animalMapper = session.getMapper(AnimalMapper.class);
            return animalMapper.findAll();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Animal findById(int id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            AnimalMapper animalMapper = session.getMapper(AnimalMapper.class);
            return animalMapper.findById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
