package com.supensour.library.sample.controller;

import com.supensour.library.web.error.BaseErrorControllerHandler;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Suprayan Yapura
 * @since June 07, 2019
 */
@Slf4j
@RestControllerAdvice
public class ErrorControllerHandler implements BaseErrorControllerHandler {

  @Override
  public Logger getLogger() {
    return log;
  }

}
