package org.example.daos.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.FarmSupplyProductBought;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.myBatis.factory.SessionFactory;
import org.example.utils.myBatis.mappers.AnimalMapper;
import org.example.utils.myBatis.mappers.FarmSupplyProductBoughtMapper;

import java.sql.Connection;
import java.util.List;

public class FarmSupplyProductBoughtMyBatis implements IDAO<FarmSupplyProductBought> {

    private static final Logger logger = LogManager.getLogger();
    SqlSessionFactory sqlSessionFactory;

    public FarmSupplyProductBoughtMyBatis(){
        SessionFactory<FarmSupplyProductBoughtMapper> sessionFactory = new SessionFactory<>(FarmSupplyProductBoughtMapper.class);
        this.sqlSessionFactory = sessionFactory.getSqlSessionFactory();

    }

    @Override
    public FarmSupplyProductBought insert(FarmSupplyProductBought data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            FarmSupplyProductBoughtMapper fMapper = session.getMapper(FarmSupplyProductBoughtMapper.class);
            Integer affected = fMapper.insert(data);
            session.commit();
            if(affected==0){
                return null;
            }
            return fMapper.findById(data.getId());

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int updateById(int id, FarmSupplyProductBought data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            FarmSupplyProductBoughtMapper fMapper = session.getMapper(FarmSupplyProductBoughtMapper.class);
            return fMapper.updateById(id,data);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            FarmSupplyProductBoughtMapper fMapper = session.getMapper(FarmSupplyProductBoughtMapper.class);
            return fMapper.deleteById(id);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<FarmSupplyProductBought> findAll() throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            FarmSupplyProductBoughtMapper fMapper = session.getMapper(FarmSupplyProductBoughtMapper.class);
            return fMapper.findAll();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public FarmSupplyProductBought findById(int id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            FarmSupplyProductBoughtMapper fMapper = session.getMapper(FarmSupplyProductBoughtMapper.class);
            return fMapper.findById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
