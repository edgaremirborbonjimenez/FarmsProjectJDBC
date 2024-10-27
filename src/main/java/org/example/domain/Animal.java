package org.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.xml.bind.annotation.*;

// Note : Add element o attribute annotation on getter y setter
// is to can manage better the way the properties are serialized
// on getter and deserialized on setter (this could have be made with XmlAccessType.PROPERTY)
//And you specify this in the @XmlAccesorType there are 4 options
//XmlAccessType.FIELD(only take attributes does not matter is public or private),
//XmlAccessType.NONE(does not take from anywhere),
//XmlAccessType.PROPERTY(take it form getter setter),
//XmlAccessType.PUBLIC_MEMBER(take all public attributes)

@XmlRootElement(name="animal")
@XmlType(propOrder = {"id","name"})
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({"id","name"})
public class Animal {
    @XmlAttribute
    @JsonProperty("id")
    int id;
    @JsonProperty("name")
    String name;
    public Animal(){}

    public int getId() {
        return id;
    }

//    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

//    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
