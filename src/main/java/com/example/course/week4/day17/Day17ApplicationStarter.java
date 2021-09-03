package com.example.course.week4.day17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *    ->  "/*" dispatcherServlet  -> /endpoint handlerMapping -> Controller
 *                  |
 *               view resolver
 *                 | (model and view)
 *              view
 *
 *
 *    ->  "/*" dispatcherServlet  -> /endpoint handlerMapping -> Controller
 *                  |
 *             HttpMessageConverter(default Jackson)
 *                 |
 *             response
 *
 *
 *      get student by id
 *           endpoint: /student/{id}  path variable
 *           http method: get
 *           status code: 200 / 400 / 500 / 404
 *           response body:
 *                  {
 *                      content: {
 *                          "id" : "..",
 *                          "name" : "Tom"
 *                      }
 *                  }
 *      1. table student(id, name, isActive, lastLoginTime)
 *      2. entity / interface / configuration / DTO class
 *          repository interface {
 *              Student getStudentById(String id);
 *          }
 *          service interface {
 *              StudentDTO getStudentById(String id);
 *          }
 *      3. unit test / function test
 *      4. RepositoryImpl
 *         ServiceImpl(inject repository)
 *         Controller(inject service) {
 *              @GetMapping("/students/{id}")
 *              public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id) {
 *                  if(id is not valid) {
 *                      throw .. 400
 *                  }
 *                  StudentDTO s = service.getStudentById(id);
 *                  if(s == null) {
 *                      throw .. 404
 *                  }
 *                  return new ResponseEntity<>(s, HttpStatus.OK);
 *              }
 *
 *              @ExceptionHandler(..)
 *              public ResponseEntity<ExceptionResponse> ..() {
 *
 *                  return..
 *              }
 *         }
 *
 *
 *         [db - repository] - [service - controller] -  frontend
 *               model                 controller            view
 *
 *      renqing.yang@antra.com
 */
@SpringBootApplication
public class Day17ApplicationStarter {
    public static void main(String[] args) {
        SpringApplication.run(Day17ApplicationStarter.class, args);
    }
}
