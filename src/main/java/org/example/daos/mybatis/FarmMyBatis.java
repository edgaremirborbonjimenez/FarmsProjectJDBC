package org.example.daos.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Farm;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.myBatis.factory.SessionFactory;
import org.example.utils.myBatis.mappers.AnimalMapper;
import org.example.utils.myBatis.mappers.FarmMapper;

import java.sql.Connection;
import java.util.List;

public class FarmMyBatis implements IDAO<Farm> {
    private static final Logger logger = LogManager.getLogger();
    SqlSessionFactory sqlSessionFactory;

    public FarmMyBatis(){
        SessionFactory<FarmMapper> sessionFactory = new SessionFactory<>(FarmMapper.class);
        this.sqlSessionFactory = sessionFactory.getSqlSessionFactory();
    }

    @Override
    public Farm insert(Farm data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            FarmMapper farmMapper = session.getMapper(FarmMapper.class);
            Integer affected = farmMapper.insert(data);
            session.commit();
            if(affected==0){
                return null;
            }
            return farmMapper.findById(data.getId());

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int updateById(int id, Farm data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            FarmMapper farmMapper = session.getMapper(FarmMapper.class);
            return farmMapper.updateById(id,data);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            FarmMapper farmMapper = session.getMapper(FarmMapper.class);
            return farmMapper.deleteById(id);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Farm> findAll() throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            FarmMapper farmMapper = session.getMapper(FarmMapper.class);
            return farmMapper.findAll();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Farm findById(int id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            FarmMapper farmMapper = session.getMapper(FarmMapper.class);
            return farmMapper.findById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {
    }
}
