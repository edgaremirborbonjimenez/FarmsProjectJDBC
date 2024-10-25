package org.example.daos.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Store;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.myBatis.factory.SessionFactory;
import org.example.utils.myBatis.mappers.AnimalMapper;
import org.example.utils.myBatis.mappers.StoreMapper;

import java.sql.Connection;
import java.util.List;

public class StoreMyBatis implements IDAO<Store> {
    private static final Logger logger = LogManager.getLogger();
    SqlSessionFactory sqlSessionFactory;

    public StoreMyBatis(){
        SessionFactory<StoreMapper> sessionFactory = new SessionFactory<>(StoreMapper.class);
        this.sqlSessionFactory = sessionFactory.getSqlSessionFactory();
    }

    @Override
    public Store insert(Store data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            StoreMapper storeMapper = session.getMapper(StoreMapper.class);
            Integer affected = storeMapper.insert(data);
            session.commit();
            if(affected==0){
                return null;
            }
            return storeMapper.findById(data.getId());

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int updateById(int id, Store data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            StoreMapper storeMapper = session.getMapper(StoreMapper.class);
            return storeMapper.updateById(id,data);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            StoreMapper storeMapper = session.getMapper(StoreMapper.class);
            return storeMapper.deleteById(id);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Store> findAll() throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            StoreMapper storeMapper = session.getMapper(StoreMapper.class);
            return storeMapper.findAll();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Store findById(int id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            StoreMapper storeMapper = session.getMapper(StoreMapper.class);
            return storeMapper.findById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
