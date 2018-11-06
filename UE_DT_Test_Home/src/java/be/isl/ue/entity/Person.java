/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.isl.ue.entity;

import java.util.Date;
import java.util.Objects;



/**
 *
 * @author davetones
 */
public class Person implements Entity, Comparable<Person>{

    private Integer personId;
    private String  personFirstName;
    private String  personLastName;
    private String  personMobile;
    private String  personEmail;
    private String  personAddress;
    private String  personPostalCode;
    private String  personCity;
    private String  personCountry;
    private boolean personIsTeacher;
    private Date    personDateOfBirth;
    private boolean personIsJuryMember;

    public Person() {
    }

    public Person(int id, String lastName, String firstName) {
        this.personId = id;
        this.personLastName = lastName;
        this.personFirstName = firstName;
    }


    public Person(int id, String lastName, String firstName, String email, String mobile, String address, String postalCode, String city, String country, Date dateOfBirth) {
        this.personId       = id;
        this.personLastName       = lastName;
        this.personFirstName      = firstName;
        this.personEmail          = email;
        this.personMobile         = mobile;
        
        this.personAddress        = address;
        this.personPostalCode     = postalCode;
        this.personCity           = city;
        this.personCountry        = country;
        this.personDateOfBirth    = dateOfBirth;
    }

  


    public Integer getPersonId() {
        return personId;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public String getPersonPostalCode() {
        return personPostalCode;
    }

    public String getPersonCity() {
        return personCity;
    }

    public String getPersonCountry() {
        return personCountry;
    }

    public boolean isPersonIsTeacher() {
        return personIsTeacher;
    }

    public Date getPersonDateOfBirth() {
        return personDateOfBirth;
    }

    public boolean isPersonIsJuryMember() {
        return personIsJuryMember;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public void setPersonPostalCode(String personPostalCode) {
        this.personPostalCode = personPostalCode;
    }

    public void setPersonCity(String personCity) {
        this.personCity = personCity;
    }

    public void setPersonCountry(String personCountry) {
        this.personCountry = personCountry;
    }

    public void setPersonIsTeacher(boolean personIsTeacher) {
        this.personIsTeacher = personIsTeacher;
    }

    public void setPersonDateOfBirth(Date personDateOfBirth) {
        this.personDateOfBirth = personDateOfBirth;
    }

    public void setPersonIsJuryMember(boolean personIsJuryMember) {
        this.personIsJuryMember = personIsJuryMember;
    }

    
        @Override
    public Integer getId() {
        return personId;
    }

    @Override
    public int compareTo(Person o) {
        return this.personLastName.compareTo(o.personLastName);
    }

    @Override
    public String toString() {
        return "Person{" + "personId=" + personId + ", firsName=" + personFirstName + ", lastName=" + personLastName + ", mobile=" + personMobile + ", email=" + personEmail + ", address=" + personAddress + ", postalCode=" + personPostalCode + ", city=" + personCity + ", contry=" + personCountry + ", isTeacher=" + personIsTeacher + ", dateOfBirth=" + personDateOfBirth + ", isJuryMember=" + personIsJuryMember + '}';
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.personIsTeacher != other.personIsTeacher) {
            return false;
        }
        if (this.personIsJuryMember != other.personIsJuryMember) {
            return false;
        }
        if (!Objects.equals(this.personFirstName, other.personFirstName)) {
            return false;
        }
        if (!Objects.equals(this.personLastName, other.personLastName)) {
            return false;
        }
        if (!Objects.equals(this.personMobile, other.personMobile)) {
            return false;
        }
        if (!Objects.equals(this.personEmail, other.personEmail)) {
            return false;
        }
        if (!Objects.equals(this.personAddress, other.personAddress)) {
            return false;
        }
        if (!Objects.equals(this.personPostalCode, other.personPostalCode)) {
            return false;
        }
        if (!Objects.equals(this.personCity, other.personCity)) {
            return false;
        }
        if (!Objects.equals(this.personCountry, other.personCountry)) {
            return false;
        }
        if (!Objects.equals(this.personId, other.personId)) {
            return false;
        }
        if (!Objects.equals(this.personDateOfBirth, other.personDateOfBirth)) {
            return false;
        }
        return true;
    }
    
    
    

    
    
    
}
