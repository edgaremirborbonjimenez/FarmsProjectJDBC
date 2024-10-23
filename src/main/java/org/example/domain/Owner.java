package org.example.domain;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "owner")
@XmlType(propOrder = {"id","fullName","phone","email"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Owner {
    @XmlAttribute
    int id;
    String fullName;
    String phone;
    String email;

    public Owner(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "fullName=" + fullName +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
