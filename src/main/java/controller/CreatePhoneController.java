package controller;

import db.dao.MobilePhoneDao;
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
        MobilePhone mobilePhone = new MobilePhone("Siemens c35", 3242, "not bad");
        mobilePhoneDao.addPhone(mobilePhone);
        modelAndView.addObject("phone", mobilePhoneDao.getPhoneById(2));
        return modelAndView;
    }
}
