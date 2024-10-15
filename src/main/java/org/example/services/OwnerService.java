package org.example.services;

import org.example.domain.Owner;
import org.example.interfaces.IDAO;
import org.example.interfaces.IService;

import javax.management.OperationsException;
import java.util.List;

public class OwnerService implements IService<Owner> {

    IDAO<Owner> ownerIDAO;

    public OwnerService(IDAO<Owner> ownerIDAO){
        this.ownerIDAO = ownerIDAO;
    }
    @Override
    public Owner insert(Owner data) throws Exception {
        return ownerIDAO.insert(data);
    }

    @Override
    public int updateById(int id, Owner data) throws Exception {
        return ownerIDAO.updateById(id,data);
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return ownerIDAO.deleteById(id);
    }

    @Override
    public List<Owner> findAll() throws Exception {
        return ownerIDAO.findAll();
    }

    @Override
    public Owner findById(int id) throws Exception {
        return ownerIDAO.findById(id);
    }
}
