package org.prathmesh.shopping_backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelathCheck {

    @GetMapping("/check")
    public String hello(){
        return "Runnimg";
    }
}
