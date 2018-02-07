package controller;

import db.entities.MobilePhone;
import db.repos.PhoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JpaController {
    @Autowired
    PhoneRepo phoneRepo;

    @RequestMapping(value = "/showJpa", method = RequestMethod.GET)
    public ModelAndView showdata(){
        ModelAndView modelAndView = new ModelAndView("data");
        Iterable<MobilePhone> all;
        MobilePhone siemens_c398 = phoneRepo.findByModel("Siemens c398");
//        MobilePhone mobilePhone = all.iterator().next();
        modelAndView.addObject("phone", siemens_c398);
        return modelAndView;
    }
}
