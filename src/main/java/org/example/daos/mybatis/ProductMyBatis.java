package org.example.daos.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.Product;
import org.example.interfaces.IConnection;
import org.example.interfaces.IDAO;
import org.example.utils.myBatis.factory.SessionFactory;
import org.example.utils.myBatis.mappers.AnimalMapper;
import org.example.utils.myBatis.mappers.ProductMapper;

import java.sql.Connection;
import java.util.List;

public class ProductMyBatis implements IDAO<Product> {

    private static final Logger logger = LogManager.getLogger();
    SqlSessionFactory sqlSessionFactory;

    public ProductMyBatis(){
        SessionFactory<ProductMapper> sessionFactory = new SessionFactory<>(ProductMapper.class);
        this.sqlSessionFactory = sessionFactory.getSqlSessionFactory();
    }

    @Override
    public Product insert(Product data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            Integer affected = productMapper.insert(data);
            session.commit();
            if(affected==0){
                return null;
            }
            return productMapper.findById(data.getId());

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int updateById(int id, Product data) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            return productMapper.updateById(id,data);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession(true)){
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            return productMapper.deleteById(id);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Product> findAll() throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            return productMapper.findAll();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Product findById(int id) throws Exception {
        try(SqlSession session = sqlSessionFactory.openSession()){
            ProductMapper productMapper = session.getMapper(ProductMapper.class);
            return productMapper.findById(id);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void setPoolConnection(IConnection<Connection> poolConnection) {

    }
}
