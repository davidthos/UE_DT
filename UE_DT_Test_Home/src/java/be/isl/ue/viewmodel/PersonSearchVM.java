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

    String      personSearchVMFirsName;
    String      personSearchVMLastName;
    String      personSearchVMMobile;
    String      personSearchVMEmail;
    String      personSearchVMAddress;
    String      personSearchVMPostalCode;
    String      personSearchVMCity;
    String      personSearchVMCountry;
    boolean     personSearchVMIsTeacher;
    Date        personSearchVMDateOfBirth;
    boolean     personSearchVMIsJuryMember;

    public PersonSearchVM() {
    }


    public String getPersonSearchVMFirstName() {
        return personSearchVMFirsName;
    }

    public String getPersonSearchVMLastName() {
        return personSearchVMLastName;
    }

    public String getPersonSearchVMMobile() {
        return personSearchVMMobile;
    }

    public String getPersonSearchVMEmail() {
        return personSearchVMEmail;
    }

    public String getPersonSearchVMAddress() {
        return personSearchVMAddress;
    }

    public String getPersonSearchVMPostalCode() {
        return personSearchVMPostalCode;
    }

    public String getPersonSearchVMCity() {
        return personSearchVMCity;
    }

    public String getPersonSearchVMCountry() {
        return personSearchVMCountry;
    }

    public boolean isPersonSearchVMIsTeacher() {
        return personSearchVMIsTeacher;
    }

    public Date getPersonSearchVMDateOfBirth() {
        return personSearchVMDateOfBirth;
    }

    public boolean isPersonSearchVMIsJuryMember() {
        return personSearchVMIsJuryMember;
    }


    public void setPersonSearchVMFirstName(String firsName) {
        this.personSearchVMFirsName = firsName;
    }

    public void setPersonSearchVMLastName(String personSearchVMLastName) {
        this.personSearchVMLastName = personSearchVMLastName;
    }

    public void setPersonSearchVMMobile(String personSearchVMMobile) {
        this.personSearchVMMobile = personSearchVMMobile;
    }

    public void setPersonSearchVMEmail(String personSearchVMEmail) {
        this.personSearchVMEmail = personSearchVMEmail;
    }

    public void setPersonSearchVMAddress(String personSearchVMAddress) {
        this.personSearchVMAddress = personSearchVMAddress;
    }

    public void setPersonSearchVMPostalCode(String personSearchVMPostalCode) {
        this.personSearchVMPostalCode = personSearchVMPostalCode;
    }

    public void setPersonSearchVMCity(String personSearchVMCity) {
        this.personSearchVMCity = personSearchVMCity;
    }

    public void setPersonSearchVMCountry(String personSearchVMCountry) {
        this.personSearchVMCountry = personSearchVMCountry;
    }

    public void setPersonSearchVMIsTeacher(boolean personSearchVMIsTeacher) {
        this.personSearchVMIsTeacher = personSearchVMIsTeacher;
    }

    public void setPersonSearchVMDateOfBirth(Date personSearchVMDateOfBirth) {
        this.personSearchVMDateOfBirth = personSearchVMDateOfBirth;
    }

    public void setPersonSearchVMIsJuryMember(boolean personSearchVMIsJuryMember) {
        this.personSearchVMIsJuryMember = personSearchVMIsJuryMember;
    }
}
