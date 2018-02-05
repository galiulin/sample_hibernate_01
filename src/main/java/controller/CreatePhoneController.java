package controller;

import db.dao.MobilePhoneDao;
import db.entities.Certificate;
import db.entities.Manufacturer;
import db.entities.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CreatePhoneController {
    @Autowired
    private MobilePhoneDao mobilePhoneDao;


    @RequestMapping(value = "/createPhone", method = RequestMethod.GET)
    public ModelAndView createPhone(){
        ModelAndView modelAndView = new ModelAndView("data");
        Certificate certificate = new Certificate();
        MobilePhone mobilePhone = new MobilePhone("Siemens c35", 3242, "not bad", certificate);
        certificate.setPhone(mobilePhone);
        certificate.setCertNumber("9273847HJ");
        Manufacturer manufacturer=new Manufacturer("Siemens AG", "Germany");
        mobilePhone.setManufacturer(manufacturer);

        mobilePhoneDao.addPhone(mobilePhone);
        modelAndView.addObject("phone", mobilePhoneDao.getPhoneById(2));
        return modelAndView;
    }
}
