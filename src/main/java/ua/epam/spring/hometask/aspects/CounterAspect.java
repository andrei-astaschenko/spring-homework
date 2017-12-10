package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 */
@Aspect
@Component
public class CounterAspect {

    private int accessEventByNameCounter = 0;
    private int accessEventPriceCounter = 0;
    private int accessEventTicketCounter = 0;


    @Pointcut("execution(* ua.epam.spring.hometask.service.EventService.getByName(..))")
    private void nameCounter() {}

    @After("nameCounter()")
    private void incEventNameCounter() {
        accessEventByNameCounter++;
        System.out.println("Events was searched by name " + accessEventByNameCounter + " times");
    }
}
