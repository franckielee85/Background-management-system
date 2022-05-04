package com.example.demo.controller;

import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author fuyao lee
 * @date 2022/5/3 18:46
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @PostMapping("login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @GetMapping({"info"})
    public Result userinfo() {
        return Result.ok().data("roles", "admin").data("name", "fuyao").data("avatar", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2F2021%2Fedpic%2F9b%2F44%2F1c%2F9b441c7299e1294079dd569ae03989f8_1.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1647835295&t=f70b94b03e72b6fe69bcf64c6fcddda6");
    }
}
