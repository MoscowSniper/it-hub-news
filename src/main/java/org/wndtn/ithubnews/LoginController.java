package org.wndtn.ithubnews;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "admin/login";  // вернет шаблон admin/login.html
    }
    
}
