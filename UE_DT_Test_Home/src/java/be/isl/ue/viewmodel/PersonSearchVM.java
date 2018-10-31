/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.ue.viewmodel;

import java.util.Date;

/**
 *
 * @author davetones
 */
public class PersonSearchVM {

    String firsName;
    String lastName;
    String mobile;
    String email;
    String address;
    String postalCode;
    String city;
    String contry;
    boolean isTeacher;
    Date dateOfBirth;
    boolean isJuryMember;

    public PersonSearchVM() {
    }

    public PersonSearchVM(String lastName, String firstName) {
       
        this.lastName = lastName;
        this.firsName = firstName;
    }


    public String getFirstName() {
        return firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getContry() {
        return contry;
    }

    public boolean isIsTeacher() {
        return isTeacher;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isIsJuryMember() {
        return isJuryMember;
    }


    public void setFirstName(String firsName) {
        this.firsName = firsName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }

    public void setIsTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setIsJuryMember(boolean isJuryMember) {
        this.isJuryMember = isJuryMember;
    }
}
