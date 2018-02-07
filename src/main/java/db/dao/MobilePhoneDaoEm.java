package db.dao;


import db.entities.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * */
@Repository
public class MobilePhoneDaoEm {

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManagerFactory emf;

    public MobilePhone getPhoneById(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MobilePhone phone = em.find(MobilePhone.class, id);
        em.getTransaction().commit();
        em.close();
        return phone;
    }
}
