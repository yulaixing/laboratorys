package cn.techwolf.bladestorm.experiment.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }




    @RequestMapping("test")
    public String test(){
        return "ok";
    }


}
