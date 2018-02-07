package db.repos;

import db.entities.MobilePhone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepo extends CrudRepository<MobilePhone, Long> {

    MobilePhone findByModel(String model);
}
