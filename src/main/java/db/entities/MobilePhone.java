package db.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mobile")
public class MobilePhone {
    private long id;
    private String model;
    private long cost;
    private String developer;
    private String recense;
    private Certificate certificate;
    private Manufacturer manufacturer;
    private List<Deal> deals = new ArrayList<>();

    public MobilePhone() {

    }

    public MobilePhone(String model, long cost, String recense, Certificate certificate) {
        this.model = model;
        this.cost = cost;
        this.recense = recense;
        this.certificate = certificate;
    }

    public MobilePhone(long id, String model, long cost, String recense) {
        this.id = id;
        this.model = model;
        this.cost = cost;
        this.recense = recense;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq_phone", sequenceName = "PHONE_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernateSeq_phone")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getRecense() {
        return recense;
    }

    public void setRecense(String recense) {
        this.recense = recense;
    }

    @OneToOne (optional = false, cascade = CascadeType.ALL)
    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "model='" + model + '\'' +
                ", cost=" + cost +
                ", recense='" + recense + '\'' +
                '}';
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    @ManyToMany(mappedBy = "mobilePhones")
    public List<Deal> getDeals() {
        return deals;
    }

    public void setDeals(List<Deal> deals) {
        this.deals = deals;
    }

    public void addDeal(Deal deal) {
        deals.add(deal);
    }
}
