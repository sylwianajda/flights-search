//package com.FlightSearch.FlightSearch.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Aspect
//@Component
//public class RequestParameterValidationAspect {
//    @Around("@annotation(com.FlightSearch.FlightSearch.controller.RequestParameterValidation) && execution(public * *(..))")
//    public Object time(final ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] requestMappingArgs = joinPoint.getArgs();
//        Boolean returnTrip = (Boolean) requestMappingArgs[4];
//        LocalDateTime returnDepartDate = (LocalDateTime) requestMappingArgs[5];
//
//        boolean requestIsValid = if(returnTrip == false){
//            r
//
//        } else if (returnTrip == true) {
//
//        }else {
//
//        }
//
//
//        if (requestIsValid) {
//            return joinPoint.proceed();
//        } else {
//            throw new IllegalArgumentException("illegal request");
//        }
//    }
//}
