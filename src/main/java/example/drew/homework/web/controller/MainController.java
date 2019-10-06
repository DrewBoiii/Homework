package example.drew.homework.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/home"})
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/cars")
    public String getCarsPage(){
        return "cars";
    }

    @GetMapping("/editor")
    public String getEditorPage(){
        return "editor";
    }

}
