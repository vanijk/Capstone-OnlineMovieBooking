package com.capstone.onlinemoviebooking.controller;

import com.capstone.onlinemoviebooking.dto.Booking;
import com.capstone.onlinemoviebooking.dto.UserDTO;
import com.capstone.onlinemoviebooking.service.SeatMapService;
import com.capstone.onlinemoviebooking.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class UserController {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    private UserServiceImpl userDetailsService;
    @Autowired
    SeatMapService seatMapService;
    @Autowired
    public UserController(UserServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login-sumit")
    private String getLoginSubmit( HttpServletRequest request, HttpServletResponse response)
    {

        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.getRequest(request,response);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getName();
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        String url = (String)request.getSession().getAttribute("url_prior_login");
        return "/admin-page";
     /*   if(url != null || url != "/sign-in" || url != "/login"){
            url = getUrl(url);
        }else if(){
            return "/admin-page";
        }
        if(url.equals("/save-booking")){
            return "redirect:/e-ticket";
        }
        System.out.println(url);
        //  model.addAttribute("userDto", new UserDTO());
        return "redirect:"+url;

        // return "test";*/
    }

    @PostMapping("/thankyou")
    private String redirectTothankyou(String test,Model model)
    {
        System.out.println(test);
        return "thankyou";//"redirect:/login";
    }
    @GetMapping("/sign-up")
    public String signUp(Model model)
    {
        model.addAttribute("userDto", new UserDTO());
        return "sign-up";
    }
 @GetMapping("/home")
  public String signUp(Model model,HttpServletRequest request)
  {
      String url = (String)request.getSession().getAttribute("url_prior_login");
      if(url != null){
         url = getUrl(url);
      }
    //  model.addAttribute("userDto", new UserDTO());
      return "url";
  }

    @PostMapping("/signup-process")
    public String signupProcess(@Valid @ModelAttribute("userDto") UserDTO userDTO, BindingResult bindingResult,Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("Message","Error in registration try again");
            log.warn("Wrong attempt"+bindingResult.getAllErrors());
            //return "sign-up";
            return "sign-in";
        }


        String message = userDetailsService.creat(userDTO);
        model.addAttribute("Message",message);
        //return "confirmation";
        return "sign-in";
    }
    @PostMapping("/guest-signup-process")
    public String GustSignupProcess(String guestEmail,Model model, HttpServletRequest request)
    {
        long userId = userDetailsService.creatGuest(guestEmail);
        Booking book = (Booking) request.getSession().getAttribute("MY_SESSION_MESSAGES");
        if (book != null) {
            seatMapService.setBookedSeatsfromBooking( book.getScreenId(), book.getSelectedDate(),book.getShowTime(),book.getSeatNumber(),userId);
            request.getSession().invalidate();
        }
        return "e-ticket";
    }
    @GetMapping("/login")
    public String getLoginPage(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request,response);
        log.info("Login page displayed");
        String referrer = request.getHeader("Referer");
        if(referrer!=null){
            request.getSession().setAttribute("url_prior_login", referrer);
        }
        model.addAttribute("userDto", new UserDTO());
        //return "login";
        return "sign-in";
    }
    @GetMapping("/sign-in")
    public String getSigninPage(Model model,HttpServletRequest request,HttpServletResponse response)
    {
        RequestCache requestCache = new HttpSessionRequestCache();
        requestCache.saveRequest(request,response);
        log.info("Login page displayed");
        String referrer = request.getHeader("Referer");
        if(referrer!=null){
            request.getSession().setAttribute("url_prior_login", referrer);
        }
        model.addAttribute("userDto", new UserDTO());
        //return "login";
        return "sign-in";
    }
    @GetMapping("/error")
    public String getErrorPage(Model model,HttpServletRequest request)
    {

        return "error";
    }

    /*  @RequestMapping("/home")
      public String getHome()
      {
          log.info("home page displayed");
          return "home";
      }*/
    String getUrl(String url){
        String[] strArr = url.split("http://localhost:8080");
        System.out.println("URL ARRAY :" +strArr.length +" "+ strArr[1]);
        return strArr[1];
    }

}
