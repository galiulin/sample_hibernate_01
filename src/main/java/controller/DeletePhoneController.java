package controller;

import db.dao.MobilePhoneDao;
import db.entities.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeletePhoneController {

    @Autowired
    private MobilePhoneDao mobilePhoneDao;

    @RequestMapping(value = "/deletePhone", method = RequestMethod.GET)
    public ModelAndView deletePhone(){
        ModelAndView modelAndView = new ModelAndView("data");
        mobilePhoneDao.deletePhoneById(2);
        MobilePhone mobilePhone = mobilePhoneDao.getPhoneById(2);
        modelAndView.addObject("phone", mobilePhone);
        return modelAndView;
    }
}
