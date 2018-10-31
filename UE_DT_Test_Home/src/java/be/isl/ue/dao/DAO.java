/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.ue.dao;

import be.isl.ue.entity.Entity;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davetones
 * @param <T>
 */
public abstract class DAO<T extends Entity >  {
    private ArrayList<T> entityList = new ArrayList();
    private ConnectDB cDB;
    private String deleteCommand;
    
    public DAO() {
        cDB = new ConnectDB();
    }

    public ConnectDB getCDB() {
        return cDB;
    }

    public ArrayList<T> getList() {
        return entityList;
    }

    public T getById(Integer id) {
        for (T entity : entityList) {
            if (Objects.equals(entity.getId(), id)) {
                return entity;
            }
        }
        return null;
    }

    public void setDeleteCommand(String deleteCommand) {
        this.deleteCommand = deleteCommand;
    }
    
    public void delete(T entity) {
        if (entity != null && entity.getId() != null) {
            try {
                Statement stmt = getCDB().getConn().createStatement();
                stmt.executeUpdate(
                        deleteCommand + entity.getId());
                entityList.remove(entity);
            } catch (SQLException ex) {
                Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void delete(Integer id) {
        if (id != null) {
            try {
                Statement stmt = getCDB().getConn().createStatement();
                stmt.executeUpdate(deleteCommand + id);
                for (T entity : entityList) {
                    if (Objects.equals(entity.getId(), id)) {
                        entityList.remove(entity);
                        break;
                    }
                }
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public abstract ArrayList<T> load();

    public abstract void save(T e);
}
