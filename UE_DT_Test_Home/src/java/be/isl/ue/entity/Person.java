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
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private String address;
    private String postalCode;
    private String city;
    private String contry;
    private boolean isTeacher;
    private Date dateOfBirth;
    private boolean isJuryMember;

    public Person() {
    }

    public Person(int id, String lastName, String firstName) {
        this.personId = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Integer getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
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

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    
        @Override
    public Integer getId() {
        return personId;
    }

    @Override
    public int compareTo(Person o) {
        return this.lastName.compareTo(o.lastName);
    }

    @Override
    public String toString() {
        return "Person{" + "personId=" + personId + ", firsName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile + ", email=" + email + ", address=" + address + ", postalCode=" + postalCode + ", city=" + city + ", contry=" + contry + ", isTeacher=" + isTeacher + ", dateOfBirth=" + dateOfBirth + ", isJuryMember=" + isJuryMember + '}';
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
        if (this.isTeacher != other.isTeacher) {
            return false;
        }
        if (this.isJuryMember != other.isJuryMember) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.mobile, other.mobile)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.postalCode, other.postalCode)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.contry, other.contry)) {
            return false;
        }
        if (!Objects.equals(this.personId, other.personId)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        return true;
    }
    
    
    

    
    
    
}
