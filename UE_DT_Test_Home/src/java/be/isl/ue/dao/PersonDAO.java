/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.ue.dao;

import be.isl.ue.entity.Person;
import be.isl.ue.viewmodel.PersonSearchVM;
import java.sql.Date;
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
        super.setDeleteCommand("delete from person where person_id = ");
    }
    
    @Override
    public ArrayList<Person> load() {
       
        try {
            String sql;
            
            sql ="SELECT "
                    + "person_id, "
                    + "last_name, "
                    + "first_name "
                    + "FROM person ";
            
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

            sql = "SELECT " // !! apparement les espaces entre les guillemets sont OVER IMPORTANT, ne fctionnait pas car oubli de l'espace entre SELECT et "
                    + "person_id, "
                    + "last_name, "
                    + "first_name, "
                    + "email, "
                    + "mobile, "
                    + "address, "
                    + "postal_code, "
                    + "city, "
                    + "country, "
                    + "date_of_birth "
                    + "FROM person ";


            where = "WHERE 1=1 ";
            if (s.getLastName() != null && s.getLastName().length() > 0) {
                where += "AND last_name like ? ";
            }
            if (s.getFirstName() != null && s.getFirstName().length() > 0) {
                where += "AND first_name like ? ";
            }
            if (s.getEmail() != null && s.getEmail().length() > 0) {
                where += "AND email like ? ";
            }
            if (s.getMobile() != null && s.getMobile().length() > 0) {
                where += "AND mobile like ? ";
            }
            
            
            if (s.getAddress() != null && s.getAddress().length() > 0) {
                where += "AND address like ? ";
            }
            if (s.getPostalCode() != null && s.getPostalCode().length() > 0) {
                where += "AND postal_code like ? ";
            }
            if (s.getCity() != null && s.getCity().length() > 0) {
                where += "AND city like ? ";
            }
            if (s.getCountry() != null && s.getCountry().length() > 0) {
                where += "AND country like ? ";
            }
            if (s.getDateOfBirth() != null) {
                where += "AND date_of_birth like ? ";
            }
     
            sql += where+ "ORDER BY last_name ";
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
            if (s.getEmail() != null && s.getEmail().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getEmail() + "%");
            }
            if (s.getMobile() != null && s.getMobile().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getMobile() + "%");
            }
            
            
            if (s.getAddress() != null && s.getAddress().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getAddress() + "%");
            }
            if (s.getPostalCode()!= null && s.getPostalCode().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getPostalCode() + "%");
            }
            if (s.getCity() != null && s.getCity().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getCity() + "%");
            }
            if (s.getCountry() != null && s.getCountry().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getCountry() + "%");
            }
            
            // !!! à voir !!!
            if (s.getDateOfBirth() != null ) {
                paramNumber++;
                stmt.setDate(1, (Date) s.getDateOfBirth());
            }
            
            try (ResultSet rs = stmt.executeQuery()) {

                super.getList().clear();
                while (rs.next()) {
                    
                    
                    super.getList().add(new Person(
                            rs.getInt("person_id"),
                            rs.getString("last_name"),
                            rs.getString("first_name"),
                            rs.getString("email"),
                            rs.getString("mobile"),
                            rs.getString("address"),
                            rs.getString("postal_code"),
                            rs.getString("city"),
                            rs.getString("country"),
                            rs.getDate("date_of_birth")));
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

    //Nouveau car j'essaye d'utiliser l'arrayList dans PersonCtrl
    public ArrayList<Person> load(Person s) {
        try {
            Statement stmt = super.getCDB().getConn().createStatement();
            try (ResultSet rs = stmt.executeQuery(
                    "select * from person "
                    + "order by last_name")) {
                super.getList().clear();
                while (rs.next()) {
                    super.getList().add(new Person(
                            rs.getInt("person_id"),
                            rs.getString("last_name"),
                            rs.getString("first_name")));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return super.getList();
        }
    }
    
}
