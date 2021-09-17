//package friendsSite.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//
//import friendsSite.dto.CMRespDto;
//
//@Component
//@Aspect
//public class BindingAdvice {
//		
////	private static final Logger log = LoggerFactory.getLogger(BindingAdvice.class);
//	
//	@Around("execution(* friendsSite.controller.api..*Controller.*(..))")
//	public Object validCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
//		String method = proceedingJoinPoint.getSignature().getName();
//		
//		System.out.println("type : " + type);
//		System.out.println("method : " + method);
//		
//		Object[] args = proceedingJoinPoint.getArgs();
//		for(Object arg : args) {
//			if(arg instanceof BindingResult) {
//				BindingResult bindingResult = (BindingResult) arg;
//				
//				if(bindingResult.hasErrors()) {
//					Map<String, String> errorMap = new HashMap<>();
//					for(FieldError error : bindingResult.getFieldErrors()) {
//						errorMap.put(error.getField(), error.getDefaultMessage());
////						log.warn(type+"."+method+"() => Field : "+error.getField()+", Message : " + error.getDefaultMessage());
//					}
//					return new ResponseEntity<>(new CMRespDto<>(-1, errorMap.toString(), null), HttpStatus.BAD_REQUEST);
//				}
//			}
//		}
//		return proceedingJoinPoint.proceed();
//	}
//	
//}
