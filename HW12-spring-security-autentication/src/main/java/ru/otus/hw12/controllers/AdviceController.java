package ru.otus.hw12.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.hw12.exceptions.NotFoundException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNPE(NotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("wrongID");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

}
