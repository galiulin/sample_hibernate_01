package controller;

import db.entities.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetPhoneController {

    @Autowired
    private db.dao.MobilePhoneDao mobilePhoneDao;

    @RequestMapping(value = "/getPhone", method = RequestMethod.GET)
    public ModelAndView getPhone(){
        ModelAndView modelAndView = new ModelAndView("data");
        MobilePhone phone = mobilePhoneDao.getPhoneByIdCriteria(154);
        System.out.println(phone);
        modelAndView.addObject("phone", phone);
        return modelAndView;
    }
}
