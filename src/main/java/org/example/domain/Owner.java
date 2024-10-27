package org.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "owner")
@XmlType(propOrder = {"id","fullName","phone","email"})
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({"id","fullName","phone","email"})
public class Owner {
    @XmlAttribute
    @JsonProperty("id")
    int id;
    @JsonProperty("fullName")
    String fullName;
    @JsonProperty("phone")
    String phone;
    @JsonProperty("email")
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
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
