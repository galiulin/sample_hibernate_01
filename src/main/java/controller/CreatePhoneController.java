package controller;

import db.dao.DealDao;
import db.dao.MobilePhoneDao;
import db.entities.Certificate;
import db.entities.Deal;
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

    @Autowired
    private DealDao dealDao;


    @RequestMapping(value = "/createPhone", method = RequestMethod.GET)
    public ModelAndView createPhone() {
        Deal deal = new Deal("Греф Монте Кристо");

        ModelAndView modelAndView = new ModelAndView("data");

        Certificate certificate = new Certificate();
        certificate.setCertNumber("9273847HJ");

        MobilePhone siemensC65 = new MobilePhone("Siemens c35", 3242, "not bad", certificate);
        certificate.setPhone(siemensC65);

        Manufacturer manufacturer = new Manufacturer("Siemens AG", "Germany");
        siemensC65.setManufacturer(manufacturer);

        siemensC65.addDeal(deal);

        MobilePhone iphone = new MobilePhone("iphone 3G", 4900, "good", new Certificate());
        iphone.getCertificate().setPhone(iphone);
        iphone.getCertificate().setCertNumber("1234Iphone");

        Manufacturer ipho = new Manufacturer();
        ipho.setCountry("America");
        iphone.setManufacturer(ipho);

        deal.addMobilePhone(iphone);




        deal.addMobilePhone(siemensC65);


        mobilePhoneDao.addPhone(iphone);
        mobilePhoneDao.addPhone(siemensC65);
        dealDao.saveDeal(deal);
        modelAndView.addObject("phone", mobilePhoneDao.getPhoneById(2));
        return modelAndView;
    }
}
