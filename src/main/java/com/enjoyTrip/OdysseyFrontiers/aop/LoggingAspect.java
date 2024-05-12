package com.enjoyTrip.OdysseyFrontiers.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

//	@Before(value = "execution(* com.ssafy.board.model.mapper.Board*.*(..))")
//	public void loggin(JoinPoint joinPoint) {
//		logger.debug("before call method : {} ", joinPoint.getSignature());
//		logger.debug("메서드 선언부 : {} 전달 파라미터 : {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
//	}

	@Around(value = "execution(* com.enjoyTrip.OdysseyFrontiers.*.model.mapper.*.*(..))")
	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug("around call method : {} ", joinPoint.getSignature());
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		Object proceed = joinPoint.proceed();

		stopWatch.stop();

		logger.debug("summary : {}", stopWatch.shortSummary());
		logger.debug("totalTime : {}", stopWatch.getTotalTimeMillis());
		logger.debug("pretty : {}", stopWatch.prettyPrint());

		return proceed;
	}


}