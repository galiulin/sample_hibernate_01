package db.entities;

import javax.persistence.*;

@Entity
@Table(name = "CERTIFICATE")
public class Certificate {
    private long id;
    private String certNumber;
    private MobilePhone mobilePhone;

    public Certificate() {
    }

    public Certificate(String certNumber, MobilePhone mobilePhone) {
        this.certNumber = certNumber;

    }

    @Id
    @SequenceGenerator(name = "hibernateSeq_sertificate", sequenceName = "CERTIFICATE_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernateSeq_sertificate")
    @Column(name = "certificate_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCertNumber() {
        return certNumber;
    }

    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }

    @OneToOne(optional = false, mappedBy = "certificate")
    public MobilePhone getPhone() {
        return mobilePhone;
    }

    public void setPhone(MobilePhone phone) {
        this.mobilePhone = phone;
    }
}
