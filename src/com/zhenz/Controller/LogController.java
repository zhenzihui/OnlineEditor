package com.zhenz.Controller;

import com.zhenz.Entity.Log;
import com.zhenz.Entity.User;
import com.zhenz.Service.LogService;
import com.zhenz.Service.UserService;
import com.zhenz.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


@Controller
@RequestMapping("log")
public class LogController {
        @Autowired
        LogService logService;
        @Autowired
        UserService userService;


    private Message message = new Message();
        @RequestMapping(value = "/user{id}.do",method = RequestMethod.GET)
        public ModelAndView showLog(@PathVariable("id") int uid)
        {
            ModelAndView mv = new ModelAndView("logs/log");
            List<Log> logs=logService.getLogsByUserId(uid);


            mv.addObject("logs",logs);
            mv.addObject("uid",uid);
            return mv;
        }

        @RequestMapping(value = "/flush.do",method = RequestMethod.POST)
        @ResponseBody
        public String flushLogs()
        {
            logService.flush();
            message.setStatus(Message.success);
            return message.toString();
        }

        @RequestMapping(value = "/flush{id}.do",method = RequestMethod.POST)
        @ResponseBody
        public String flushLogs(@PathVariable("id") int uid)
        {
            logService.flushByUserId(uid);
            message.setStatus(Message.success);
            return message.toString();
        }

        @RequestMapping(value = "/users",method = RequestMethod.GET)
        public ModelAndView getUsers()
        {
            ModelAndView mv = new ModelAndView("logs/users");
            List<User> users=userService.all();
            mv.addObject("users",users);
            return mv;
        }

}
