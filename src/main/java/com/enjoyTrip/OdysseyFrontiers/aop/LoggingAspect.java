package com.enjoyTrip.OdysseyFrontiers.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
	@Around(value = "execution(* com.enjoyTrip.OdysseyFrontiers.*.model.mapper.*.*(..))")
	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("around call method : {} ", joinPoint.getSignature());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object proceed = joinPoint.proceed();

		stopWatch.stop();

		log.info("summary : {}", stopWatch.shortSummary());
		log.info("totalTime : {}", stopWatch.getTotalTimeMillis());
		log.info("pretty : {}", stopWatch.prettyPrint());


		return proceed;
	}


}
