# Machine Service
Below is the spesification for engine service

## Feature & Scenario

```
Feature: Calculate production cost
  Scenario: client makes call to POST /widget using fueltype PETROL
  	Given widget application is up
    When the client calls /widget with fueltype PETROL and quantity 10
    Then the client receives status code of 200 
    And the client receives productionCost 18.0

  Scenario: client makes call to POST /widget using fueltype WOOD
    Given widget application is up
    When the client calls /widget with fueltype WOOD and quantity 10
    Then the client receives status code of 200
    And the client receives productionCost 21.75
    
   Scenario: client makes call to POST /widget using fueltype DIESEL
    Given widget application is up
    When the client calls /widget with fueltype DIESEL and quantity 10
    Then the client receives status code of 200
    And the client receives productionCost 24.0
    
   Scenario: client makes call to POST /widget using fueltype COAL
    Given widget application is up
    When the client calls /widget with fueltype COAL and quantity 10
    Then the client receives status code of 200
    And the client receives productionCost 28.25  
```
## Service
URL : POST /v2/widget
### Sample request
```
{
    "engineType":"DIESEL",
    "quantity":10
}  
```
### Sample success response
```
{
    "productionCost": 24.0
}	
```
### Sample expected error response
```	
{
    "timestamp": "2022-01-09T11:34:08.813+00:00",
    "status": 500,
    "error": "Internal Server Error",
    "trace": "com.fasterxml.jackson.core.JsonParseException: Unexpected character ('<' (code 60)): expected a valid value (JSON String, Number, Array, Object or token 'null', 'true' or 'false')\n at [Source: (String)\"<html><body><h1>Whitelabel Error Page</h1><p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p><div id='created'>Sun Jan 09 19:34:08 SGT 2022</div><div>There was an unexpected error (type=Internal Server Error, status=500).</div><div>Fuel type unknown</div><div style='white-space:pre-wrap;'>java.lang.IllegalArgumentException: Fuel type unknown\r\n\\tat com.worldline.interview.controller.UtilsController.post(UtilsController.java:35)\r\n\\tat com.worldline.inter\"[truncated 6123 chars]; line: 1, column: 2]\r\n\tat com.fasterxml.jackson.core.JsonParser._constructError(JsonParser.java:2391)\r\n\tat com.fasterxml.jackson.core.base.ParserMinimalBase._reportError(ParserMinimalBase.java:735)\r\n\tat com.fasterxml.jackson.core.base.ParserMinimalBase._reportUnexpectedChar(ParserMinimalBase.java:659)\r\n\tat com.fasterxml.jackson.core.json.ReaderBasedJsonParser._handleOddValue(ReaderBasedJsonParser.java:2005)\r\n\tat com.fasterxml.jackson.core.json.ReaderBasedJsonParser.nextToken(ReaderBasedJsonParser.java:802)\r\n\tat com.fasterxml.jackson.databind.ObjectMapper._initForReading(ObjectMapper.java:4761)\r\n\tat com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4667)\r\n\tat com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3629)\r\n\tat com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3597)\r\n\tat com.worldline.interview.controller.EngineController.retrieveBean(EngineController.java:110)\r\n\tat com.worldline.interview.controller.EngineController.finish(EngineController.java:67)\r\n\tat com.worldline.interview.controller.EngineController$$FastClassBySpringCGLIB$$f2437cee.invoke(<generated>)\r\n\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:783)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:753)\r\n\tat org.springframework.validation.beanvalidation.MethodValidationInterceptor.invoke(MethodValidationInterceptor.java:123)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:753)\r\n\tat org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:753)\r\n\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:698)\r\n\tat com.worldline.interview.controller.EngineController$$EnhancerBySpringCGLIB$$8d94cf7c.finish(<generated>)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\r\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1067)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:963)\r\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\r\n\tat org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:909)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:681)\r\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:764)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:227)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter.doFilterInternal(WebMvcMetricsFilter.java:96)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:189)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:162)\r\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:197)\r\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97)\r\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:540)\r\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:135)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:357)\r\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:382)\r\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65)\r\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:895)\r\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1732)\r\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\r\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\r\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n\tat java.lang.Thread.run(Thread.java:748)\r\n",
    "message": "Unexpected character ('<' (code 60)): expected a valid value (JSON String, Number, Array, Object or token 'null', 'true' or 'false')\n at [Source: (String)\"<html><body><h1>Whitelabel Error Page</h1><p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p><div id='created'>Sun Jan 09 19:34:08 SGT 2022</div><div>There was an unexpected error (type=Internal Server Error, status=500).</div><div>Fuel type unknown</div><div style='white-space:pre-wrap;'>java.lang.IllegalArgumentException: Fuel type unknown\r\n\\tat com.worldline.interview.controller.UtilsController.post(UtilsController.java:35)\r\n\\tat com.worldline.inter\"[truncated 6123 chars]; line: 1, column: 2]",
    "path": "/v2/engine/stop"
}	
```

### Other url
*	health check : http://localhost:8080/actuator/health
*	swagger : http://localhost:8080/v2/api-docs

### Commands

```
Generate acceptance test first time : mvn clean test
Generate acceptance test report : mvn verify -DskipTests
Run : mvn spring-boot:run

```
note 
acceptance test report will be generated at :
[project dir]\target\cucumber-report-html\cucumber-html-reports

### Acceptance Test result
#### Feature overview
![alt text](https://github.com/tommyhutomo/worldline-tech-assessment/blob/master/v.2/machinesvc/image/featureOverview.PNG?raw=true)
	
#### Feature report
![alt text](https://github.com/tommyhutomo/worldline-tech-assessment/blob/master/v.2/machinesvc/image/featureReport.PNG?raw=true)