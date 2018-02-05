package db.dao;

import db.entities.Deal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public class DealDao {

    @Autowired
    SessionFactory factory;

    public void saveDeal(Deal deal) {
        getFromDB(session -> {
            session.save(deal);
            return true;
        });
    }

    /**
     * вспомогательный метод для работы с {@link Session}
     * используя {@link Function}
     */
    private <T> T getFromDB(Function<Session, T> function) {
        T result;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            result = function.apply(session);
            session.getTransaction().commit();
        }
        return result;
    }
}
