package com.example.course.week5;

/**
 *                    user   /students ,  /teachers
 *                    ||
 *                    \/
 *                  gateway      <->    security service
 *                 /        \
 *             service1     service2
 *              /   \
 *       service3 service4
 *
 *
 *  1. gateway (zuul, spring cloud gateway)
 *  2. security service
 *  3. discovery service (eureka)
 *  4. loadbalancer framework (ribbon(load balancing rule))
 *                      -> n1  192.168.0.0
 *      gateway ->  lb  -> n2  192.168.0.1
 *                      -> n3  192.168.0.2
 *
 *
 *                    ->  n1  192.168.0.0
 *      gateway(lb)   ->  n2  192.168.0.1
 *                    ->  n3  192.168.0.2
 *  5. configuration
 *  6. fail tolerance (hystrix(circuit breaker))
 *      service1 -> service2 (timeout / connection fail)
 *      service1 <-> hystrix default result -> service2
 *
 *  question:
 *      user upload file -> split file into 5 - 10 pieces -> upload to S3
 *
 *
 *   why message queue?
 *   what is kafka
 *   agile scrum + daily work + jenkins pipeline / cicd
 *   unit test
 */