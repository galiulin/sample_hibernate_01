package db.dao;

import db.entities.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.function.Function;

@Repository
public class MobilePhoneDao {

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManagerFactory emf;

    /**
     * Получение {@link MobilePhone} по его id
     *
     * @return MobilePhone
     */
    public MobilePhone getPhoneById(long id) {
        return getFromDB(entityManager ->
                entityManager.find(MobilePhone.class, id));
    }

    /**
     * пример запроса Native
     */
    public MobilePhone getPhoneByIdNative(long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query nativeQuery = em.createNativeQuery(
                "SELECT * FROM mobile WHERE mobile.id=" +
                        id);
        Object[] o = (Object[]) nativeQuery.getResultList().get(0);
        MobilePhone phone = new MobilePhone();
        phone.setId(id);
        BigInteger bi = (BigInteger) o[1];
        phone.setCost(bi.longValue());
//        phone.setDeveloper((String) o[2]);
        phone.setModel((String) o[3]);
        phone.setRecense((String) o[4]);
        /*fixme в настоящий момент не выставляется certificate и manufacturer*/
        em.close();
        return phone;
    }

    /**
     * пример запроса Hql
     */
    public MobilePhone getPhoneByIdHql(long id) {
        return getFromDB(session -> {
            System.out.println("user hql");
            Query query = session.createQuery("FROM MobilePhone where id=:id");
            query.setParameter("id", id);
            return (MobilePhone) query.getResultList().get(0);
        });
    }

    /**
     * пример запроса Criteria
     */
    public MobilePhone getPhoneByIdCriteria(long id) {
        return getFromDB(entityManager -> {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<MobilePhone> query = criteriaBuilder.createQuery(MobilePhone.class);
            Root<MobilePhone> from = query.from(MobilePhone.class);
            query.where(criteriaBuilder.equal(from.get("id"), id));
            return (MobilePhone) entityManager.createQuery(query).getSingleResult();
        });
    }

    /**
     * Добавление в базу данных {@link MobilePhone}
     */
    public void addPhone(MobilePhone phone) {
        getFromDB(entityManager -> {
            entityManager.persist(phone);
            return true;
        });
    }

    /**
     * Обновление в базе данных {@link MobilePhone}
     */
    public void updatePhone(MobilePhone phone) {
        getFromDB(entityManager -> {
            entityManager.merge(phone);
            return true;
        });
    }

    /**
     * Удаление из базы данных {@link MobilePhone} по его id.
     *
     * @return true если телефон с указанным id был в базе данных
     * и успешно удален.
     * <p>
     * false если телефона в базе данных не было
     */
    public boolean deletePhoneById(long id) {
        return getFromDB(entityManager -> {
            MobilePhone m = entityManager.find(MobilePhone.class, id);
            if (m != null) {
                entityManager.remove(m);
                return true;
            }
            return false;
        });

    }

    /**
     * вспомогательный метод для работы с {@link EntityManager}
     * используя {@link Function}
     */
    private <T> T getFromDB(Function<EntityManager, T> function) {
        T result;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        result = function.apply(em);
        em.getTransaction().commit();
        em.close();
        return result;
    }
}
