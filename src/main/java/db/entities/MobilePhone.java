package db.entities;


import javax.persistence.*;

@Entity
@Table(name = "mobile")
public class MobilePhone {
    private Long id;
    private String model;
    private long cost;
    private String developer;
    private String recense;

    public MobilePhone() {

    }

    public MobilePhone(String model, long cost, String recense) {
        this.model = model;
        this.cost = cost;
        this.recense = recense;
    }

    public MobilePhone(long id, String model, long cost, String recense) {
        this.id = id;
        this.model = model;
        this.cost = cost;
        this.recense = recense;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernateSeq")
    @Column(name = "mobile_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public String toString() {
        return "MobilePhone{" +
                "model='" + model + '\'' +
                ", cost=" + cost +
                ", recense='" + recense + '\'' +
                '}';
    }
}
