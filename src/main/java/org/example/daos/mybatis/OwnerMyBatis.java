package org.example.daos.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Owner;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.myBatis.factory.SessionFactory;
import org.example.utils.myBatis.mappers.AnimalMapper;
import org.example.utils.myBatis.mappers.OwnerMapper;

import java.sql.Connection;
import java.util.List;

public class OwnerMyBatis implements IDAO<Owner> {

    private static final Logger logger = LogManager.getLogger();
    SqlSessionFactory sqlSessionFactory;

    public OwnerMyBatis(){
        SessionFactory<OwnerMapper> sessionFactory = new SessionFactory<>(OwnerMapper.class);
        this.sqlSessionFactory = sessionFactory.getSqlSessionFactory();
    }

    @Override
    public Owner insert(Owner data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
            Integer affected = ownerMapper.insert(data);
            session.commit();
            if(affected==0){
                return null;
            }
            return ownerMapper.findById(data.getId());

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int updateById(int id, Owner data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
            return ownerMapper.updateById(id,data);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
            return ownerMapper.deleteById(id);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Owner> findAll() throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
            return ownerMapper.findAll();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Owner findById(int id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
            return ownerMapper.findById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
