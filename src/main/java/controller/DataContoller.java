package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DataContoller {

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ModelAndView showData(){
        ModelAndView modelAndView = new ModelAndView("data");
        return modelAndView;
    }
}
