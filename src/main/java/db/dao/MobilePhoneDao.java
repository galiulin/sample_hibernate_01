package db.dao;

import db.entities.MobilePhone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public class MobilePhoneDao {

    @Autowired
    private SessionFactory factory;

    /**
     * Получение {@link MobilePhone} по его id
     *
     * @return MobilePhone
     */
    public MobilePhone getPhoneById(long id) {
        return getFromDB(session ->
                session.get(MobilePhone.class, id));
    }

    public MobilePhone getPhoneByIdNative(long id) {
        Session session = factory.openSession();
        session.beginTransaction();
        NativeQuery query = session.createNativeQuery(
                "SELECT * FROM mobile WHERE mobile.id=" +
                        id);
        query.addEntity(MobilePhone.class);
        MobilePhone phone = (MobilePhone) query.list().get(0);
        session.close();
        return phone;
    }

    /**
     * Добавление в базу данных {@link MobilePhone}
     */
    public void addPhone(MobilePhone phone) {
        getFromDB(session -> {
            session.save(phone);
            return true;
        });
    }

    /**
     * Обновление в базе данных {@link MobilePhone}
     */
    public void updatePhone(MobilePhone phone) {
        getFromDB(session -> {
            session.update(phone);
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
        return getFromDB(session -> {
            MobilePhone m = session.get(MobilePhone.class, id);
            if (m != null) {
                session.delete(m);
                return true;
            }
            return false;
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
