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
            if (s.getPersonSearchVMLastName() != null && s.getPersonSearchVMLastName().length() > 0) {
                where += "AND last_name like ? ";
            }
            if (s.getPersonSearchVMFirstName()!= null && s.getPersonSearchVMFirstName().length() > 0) {
                where += "AND first_name like ? ";
            }
            if (s.getPersonSearchVMEmail() != null && s.getPersonSearchVMEmail().length() > 0) {
                where += "AND email like ? ";
            }
            if (s.getPersonSearchVMMobile() != null && s.getPersonSearchVMMobile().length() > 0) {
                where += "AND mobile like ? ";
            }
            
            
            if (s.getPersonSearchVMAddress() != null && s.getPersonSearchVMAddress().length() > 0) {
                where += "AND address like ? ";
            }
            if (s.getPersonSearchVMPostalCode() != null && s.getPersonSearchVMPostalCode().length() > 0) {
                where += "AND postal_code like ? ";
            }
            if (s.getPersonSearchVMCity() != null && s.getPersonSearchVMCity().length() > 0) {
                where += "AND city like ? ";
            }
            if (s.getPersonSearchVMCountry() != null && s.getPersonSearchVMCountry().length() > 0) {
                where += "AND country like ? ";
            }
            if (s.getPersonSearchVMDateOfBirth() != null) {
                where += "AND date_of_birth like ? ";
            }
     
            sql += where+ "ORDER BY last_name ";
            int paramNumber = 0;
            
            PreparedStatement stmt = super.getCDB().getConn().prepareStatement(sql);
            
            if (s.getPersonSearchVMLastName() != null && s.getPersonSearchVMLastName().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getPersonSearchVMLastName() + "%");
            }
            if (s.getPersonSearchVMFirstName() != null && s.getPersonSearchVMFirstName().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getPersonSearchVMFirstName() + "%");
            }
            if (s.getPersonSearchVMEmail() != null && s.getPersonSearchVMEmail().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getPersonSearchVMEmail() + "%");
            }
            if (s.getPersonSearchVMMobile() != null && s.getPersonSearchVMMobile().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getPersonSearchVMMobile() + "%");
            }
            
            
            if (s.getPersonSearchVMAddress() != null && s.getPersonSearchVMAddress().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getPersonSearchVMAddress() + "%");
            }
            if (s.getPersonSearchVMPostalCode()!= null && s.getPersonSearchVMPostalCode().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getPersonSearchVMPostalCode() + "%");
            }
            if (s.getPersonSearchVMCity() != null && s.getPersonSearchVMCity().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getPersonSearchVMCity() + "%");
            }
            if (s.getPersonSearchVMCountry() != null && s.getPersonSearchVMCountry().length() > 0) {
                paramNumber++;
                stmt.setString(paramNumber, "%" + s.getPersonSearchVMCountry() + "%");
            }
            
            // !!! à voir !!!
            if (s.getPersonSearchVMDateOfBirth() != null ) {
                paramNumber++;
                stmt.setDate(1, (Date) s.getPersonSearchVMDateOfBirth());
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
    public void save(Person p) {
        try {
            Statement stmt = super.getCDB().getConn().createStatement();
            String sql;
            
            if(p != null && p.getId() != null){
                sql = "UPDATE person SET "
                        + " last_name = ?"
                        + " WHERE person_id = ?";
                PreparedStatement pStmt = super.getCDB().getConn().prepareStatement(sql);
                pStmt.setString(1, p.getPersonLastName());
                pStmt.executeUpdate();
                
                /*sql = "UPDATE person SET "
                        + " last_name = ?, "
                        + " first_name = ?, "
                        + " mobile = ?, "
                        + " email = ?, "
                        + " address = ?, "
                        + " postal_Code = ?, "
                        + " city = ?, "
                        + " country = ?, "
                        + " date_of_birth = ? "
                        + " WHERE person_id = ?";
                
                PreparedStatement pStmt = super.getCDB().getConn().prepareStatement(sql);
                
                pStmt.setString(1, p.getPersonLastName());
                pStmt.setString(2, p.getPersonFirstName());
                pStmt.setString(3, p.getPersonMobile());
                pStmt.setString(4, p.getPersonEmail());
                pStmt.setString(5, p.getPersonAddress());
                pStmt.setString(6, p.getPersonPostalCode());
                pStmt.setString(7, p.getPersonCity());
                pStmt.setString(8, p.getPersonCountry());
                pStmt.setDate(9, (Date) p.getPersonDateOfBirth());
                
                pStmt.executeUpdate();*/
                
                /*sql = "UPDATE person SET "
                        + " last_name  = '"     + p.getPersonLastName()     + "'"
                        + " first_name = '"     + p.getPersonFirstName()    + "'"
                        + " mobile = '"         + p.getPersonMobile()       + "'"
                        + " email = '"          + p.getPersonEmail()        + "'"
                        + " address = '"        + p.getPersonAddress()      + "'"
                        + " postal_Code = '"    + p.getPersonPostalCode()   + "'"
                        + " city = '"           + p.getPersonCity()         + "'"
                        + " country = '"        + p.getPersonCountry()      + "'"
                        + " date_of_birth = '"  + p.getPersonDdateOfBirth() + "'"
                        + " WHERE person_id = " + p.getId();
                
                stmt.executeUpdate(sql);*/
                
            }else{
                sql = "INSERT INTO person ("
                        + "last_name, "
                        + "first_name, "
                        + "mobile, "
                        + "email, "
                        + "address, "
                        + "postal_Code, "
                        + "city, "
                        + "country, "
                        + "date_of_birth "
                        + ") VALUES ("
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "?, "
                        + "? "
                        + ")";
                
                PreparedStatement pStmt = super.getCDB().getConn().prepareStatement(sql);
                
                pStmt.setString(1, p.getPersonLastName());
                pStmt.setString(2, p.getPersonFirstName());
                pStmt.setString(3, p.getPersonMobile());
                pStmt.setString(4, p.getPersonEmail());
                pStmt.setString(5, p.getPersonAddress());
                pStmt.setString(6, p.getPersonPostalCode());
                pStmt.setString(7, p.getPersonCity());
                pStmt.setString(8, p.getPersonCountry());
                pStmt.setDate(9, (Date) p.getPersonDateOfBirth());
                
                pStmt.executeUpdate();
                
                sql = "SELECT currval (person_person_id_seq) AS id";    // Seq ?
                
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    p.setPersonId(rs.getInt("id"));
                }
                super.getList().add(p);                
                rs.close();
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(PersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Nouveau car j'essaye d'utiliser l'arrayList dans PersonCtrl
    /*public ArrayList<Person> load(Person s) {
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
    }*/
    
}
