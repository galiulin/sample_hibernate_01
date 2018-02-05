package db.entities;

import db.entities.MobilePhone;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table (name = "manufacturer")
public class Manufacturer {
    private Long id;
    private String name;
    private String country;
    private Collection<MobilePhone> phoneCollection;

    public Manufacturer() {
    }

    public Manufacturer(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq_manufacturer", sequenceName = "MANUFACTURER_SEQUENCE")
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "hibernateSeq_manufacturer")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany (mappedBy = "manufacturer", fetch = FetchType.EAGER)
    public Collection<MobilePhone> getPhoneCollection() {
        return phoneCollection;
    }

    public void setPhoneCollection(Collection<MobilePhone> phoneCollection) {
        this.phoneCollection = phoneCollection;
    }
}
