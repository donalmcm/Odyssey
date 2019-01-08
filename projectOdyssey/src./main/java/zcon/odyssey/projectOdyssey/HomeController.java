package zcon.odyssey.projectOdyssey;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    } // maps to index.html

    @RequestMapping(value = "/becomeMentor")
    public String becomeMentor() {
        return "becomeMentor";
    }

}
