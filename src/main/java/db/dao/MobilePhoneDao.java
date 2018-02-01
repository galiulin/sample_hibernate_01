package db.dao;

import db.entities.MobilePhone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MobilePhoneDao {

    @Autowired
    private SessionFactory factory;

    public MobilePhone getPhoneById(long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        MobilePhone phone = (MobilePhone) session.get(MobilePhone.class, id);
        session.getTransaction().commit();
        session.close();
        return phone;
    }
}
