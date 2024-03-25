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
    @GetMapping("/login")
    public String getLoginPage(HttpServletRequest request)
    {

        String referrer = request.getHeader("Referer");
        if(referrer!=null){
            request.getSession().setAttribute("url_prior_login", referrer);
        }

        log.info("Login page displayed" + referrer);
        return "login";
    }
    @RequestMapping("/home")
    public String getHome(HttpServletRequest request)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getName();
        boolean hasUserRoleAdmin = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        boolean hasUserRoleUser = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
        boolean hasUserRoleGuest = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_GUEST"));
        if(hasUserRoleAdmin)
            return "redirect:/admin-page";
        if(hasUserRoleUser){

            String url = (String)request.getSession().getAttribute("url_prior_login");
        }
        log.info("home page displayed");
        return "/index";
    }


    @PostMapping("/signup-process")
    public String signupProcess(@Valid @ModelAttribute("userDto") UserDTO userDTO, BindingResult bindingResult,Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("Message","Error in registration try again");
            log.warn("Wrong attempt"+bindingResult.getAllErrors());
            return "login";
        }

        String message = userDetailsService.creat(userDTO);
        model.addAttribute("Message",message);

        return "login";
    }
    @GetMapping("/guest-signup-process")
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


}
