/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.ue.dao;

import be.isl.ue.entity.Person;
import be.isl.ue.viewmodel.PersonSearchVM;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davetones
 */
public class PersonDAO extends DAO<Person> {

    public PersonDAO(){
        super();
        super.setDeleteCommand("delete from person_table where person_id = ");
    }
    
    @Override
    public ArrayList<Person> load() {
       
        try {
            String sql;
            
            sql ="SELECT"
                    + "person_id, "
                    + "last_name, "
                    + "first_name "
                    + "FROM person";
            
            Statement stmt = super.getCDB().getConn().createStatement();
            
            try (ResultSet rs = stmt.executeQuery(sql)) {
                super.getList().clear();
                while (rs.next()) {
                    super.getList().add(new Person(
                            rs.getInt("person_id"),
                            rs.getString("last_name"),
                            rs.getString("first_name")));
                }
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {          
        return super.getList();
        }
    }
    
    public ArrayList<Person> load(PersonSearchVM s) {
        try {
            String sql;
            String where;

            sql = "SELECT"
                    + "person_id, "
                    + "last_name, "
                    + "first_name "
                    + "FROM person";

            where = "WHERE 1=1 ";
            if (s.getLastName() != null && s.getLastName().length() > 0) {
                where += "AND last_name like ? ";
            }
            if (s.getFirstName() != null && s.getFirstName().length() > 0) {
                where += "AND first_name like ? ";
            }

        
            sql += where+ "ORDER BY last_name;";;
            int paramNumber = 0;
            
            PreparedStatement stmt = super.getCDB().getConn().prepareStatement(sql);
            
            if (s.getLastName() != null && s.getLastName().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getLastName() + "%");
            }
            if (s.getFirstName() != null && s.getFirstName().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getFirstName() + "%");
            }
 
            try (ResultSet rs = stmt.executeQuery()) {
                super.getList().clear();
                while (rs.next()) {
                    
                    super.getList().add(new Person(
                            rs.getInt("person_id"),
                            rs.getString("last_name"),
                            rs.getString("first_name")));
                }
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return super.getList();
        }
    }
    
    
    
    
    
    public Person getOne (Integer id){
        for (Person a : super.getList()) {
            if (Objects.equals(a.getId(), id))
                return a;
        }
        return null;
    }

    @Override
    public void save(Person e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
