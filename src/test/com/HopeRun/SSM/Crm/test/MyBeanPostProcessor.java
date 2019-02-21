package com.HopeRun.SSM.Crm.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
  @Override
  public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
    return null;
  }

  @Override
  public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
    return null;
  }
}
