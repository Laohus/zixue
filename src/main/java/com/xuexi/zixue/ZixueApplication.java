package com.xuexi.zixue;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@Controller
@SpringBootApplication
public class ZixueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZixueApplication.class, args);
    }

//    @RequestMapping("/")
//    public String login(){
//        return "/page/login.html";
//
//    }
//
//    @GetMapping ("/home")
//    public String loginhome(@RequestParam("username") String username, @RequestParam("password") String password,
//                            Model model){
//        boolean res = new connsql().checklogin(username,password);
//        if (res){
//            return "/page/home.html";
//        }else {
////            map.put("msg","输入账号信息不正确，请重试！");
//            model.addAttribute("msg","输入账号信息不正确，请重试！");
//
//            return "/";
//
//        }
//
//    }

}
