// package com.vms.demo.controller;

// import org.springframework.boot.autoconfigure.web.ErrorProperties;
// import
// org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
// import org.springframework.boot.web.servlet.error.ErrorAttributes;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;

// @Controller
// @RequestMapping("${server.error.path:${error.path:/error}}")
// public class ErrorController extends BasicErrorController {

// // ...

// public ErrorController(ErrorAttributes errorAttributes, ErrorProperties
// errorProperties) {
// super(errorAttributes, errorProperties);
// }

// // @RequestMapping
// // public ResponseEntity<Map<String, Object>> error(HttpServletRequest
// request)
// // {
// // Map<String, Object> body = getErrorAttributes(request,
// // isIncludeStackTrace(request, MediaType.ALL));
// // HttpStatus status = getStatus(request);
// // return new ResponseEntity<>(body, status);
// // }
// }