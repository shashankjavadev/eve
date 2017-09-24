/*package com.foodkonnekt.contoller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = Throwable.class)
    public ModelAndView redirectToErrorPage(Exception exception) {
        ModelAndView mav = new ModelAndView("exception");
        mav.addObject("exception", "Something went worng");
        return mav;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Exception exception) {
        ModelAndView mav = new ModelAndView("exception");
        mav.addObject("exception", "Something went wrong");
        return mav;
    }

}
*/