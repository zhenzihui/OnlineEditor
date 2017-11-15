package com.zhenz.Controller;

import com.zhenz.Entity.User;
import com.zhenz.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by Administrator on 2017/10/18.
 */



@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;


    private String error_login="error_login";
    private String error_register="error_reg";

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(@RequestParam String email,@RequestParam String password,HttpSession session)
    {

        String url=(String)session.getAttribute("intended");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User loginUser= userService.login(user);
        if(loginUser!=null)
        {
            session.setAttribute("user",loginUser);
            session.setAttribute("authcode",null);
        }else
            {
                session.setAttribute("authcode",error_login);
            }

        return url==null?"redirect:/":"redirect:"+url;
    }

    @RequestMapping(value = "/register.do",method = RequestMethod.GET)
    public ModelAndView showRegister()
    {
        ModelAndView mv=new ModelAndView("auth/register");
        return mv;
    }

    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    public String register(@RequestParam String email,@RequestParam String password,@RequestParam String confirm,HttpSession session)
    {


            if(password.equals(confirm))
        {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setType("U");
                int res=userService.register(user);
                if(res!=-1)
                {
                    user=userService.login(user);
                    session.setAttribute("user",user);
                    session.setAttribute("authcode",null);
                    return "redirect:/";
                }


                session.setAttribute("authcode",error_register);



        }
            session.setAttribute("authcode",error_register);



            session.setAttribute("authcode",error_register);


    return "redirect:/auth/register.do";

    }

    @RequestMapping(value = "/logout.do",method = RequestMethod.GET)
    public void logout(HttpServletResponse response,HttpSession session)
    {

        try {
            session.setAttribute("user",null);
            session.setAttribute("intended",null);
            PrintWriter out =response.getWriter();

            out.print("<script>location.href='/'</script>");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
