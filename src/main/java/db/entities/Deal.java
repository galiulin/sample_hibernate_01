package db.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Deals")
public class Deal {
    long id;
    private String bayer;
    private List<MobilePhone> mobilePhones = new ArrayList<>();

    public Deal() {
    }

    public Deal(String bayer) {
        this.bayer = bayer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBayer() {
        return bayer;
    }

    public void setBayer(String bayer) {
        this.bayer = bayer;
    }

    @ManyToMany
    @JoinTable(name = "deals_mobiles", joinColumns = @JoinColumn(name = "deal_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_id"))
    public List<MobilePhone> getMobilePhones() {
        return mobilePhones;
    }

    public void addMobilePhone(MobilePhone mobilePhone) {
        getMobilePhones().add(mobilePhone);
    }


    public void setMobilePhones(List<MobilePhone> mobilePhones) {
        this.mobilePhones = mobilePhones;
    }
}
