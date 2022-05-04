package com.xhd.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerException {
private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest httpServletRequest, Exception e) throws Exception {
        logger.error("Request URL: {},Exception :{}",httpServletRequest.getRequestURI(),e);

        if((AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class))!=null){
            throw  e;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",httpServletRequest.getRequestURI());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/error");
        return modelAndView;
    }
}