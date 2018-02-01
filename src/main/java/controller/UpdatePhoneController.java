package controller;


import db.entities.MobilePhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UpdatePhoneController {


    @Autowired
    private db.dao.MobilePhoneDao mobilePhoneDao;

    @RequestMapping(value = "/updatePhone", method = RequestMethod.GET)
    public ModelAndView updatePhone() {
        ModelAndView modelAndView = new ModelAndView("data");

        MobilePhone oldPhone = mobilePhoneDao.getPhoneById(2);

        System.out.println(oldPhone);

        MobilePhone updatedPhone = null;
        if (oldPhone != null) {
            oldPhone.setRecense("updated text");
            oldPhone.setModel("iphone X");
            mobilePhoneDao.updatePhone(oldPhone);
            updatedPhone = mobilePhoneDao.getPhoneById(oldPhone.getId());
        }

        modelAndView.addObject("phone", updatedPhone);
        return modelAndView;
    }
}
