package com.example.course.week5;


import java.util.concurrent.CompletableFuture;

/**
 *   monolithic(single service / server) vs microservice
 *
 *   monolithic
 *                  LoadBalancer
 *           /       |      \       \       \
 *       Node1     Node2    Node3   Node4  Node5
 *       100        100
 *   problems:
 *      1. maintain / deploy
 *      2. not using rest api / rpc / soap api
 *      3. all developers working on same project
 *      4. scalability + vertical scaling + horizontal scaling
 *      5. message queue(asynchronous request)
 *         producer(server)   ->   message queue(server)  ->   consumer(server)
 *      6. fail tolerance
 *
 *   microservice
 *
 *    user ->  service1(N1,N2,N3)   <->   service2(N1,N2)   <->   service3(N1,N2,N3)
 *  create request id: 101A     request id:101A         request id:101A
 *
 *
 *    pros:
 *      1. deploy separately
 *      2. 1 developer can work on 1 - n services
 *      3. better fail tolerance
 *      4. message queue
 *      5. different platform / languages
 *
 *    how to build a microservice
 *      1. need to handle security(subnet / public / private)
 *      2. complex logic + business boundary
 *      3. debug + co-relation id / global id
 *              snowflake : worker_id + (process_id) + timestamp + counter
 *              uuid
 *              database primary key
 *              zookeeper(?)
 *      4. centralize log (splunk)
 *      5. centralize configuration file
 *    * 6. global transaction
 *          DB1 , DB2
 *              2PC / 3PC (phases commit)
 *              SAGA pattern(message queue)
 *      7. fail tolerance
 *              circuit breaker
 *      8. service1(n1, n2, n3) -> service2(n1, n2)
 *                  \               /
 *              discovery service / registration service
 *      9. gateway
 *      10. client loadbalancer
 *    * 11. monitor..
 *    * 12. environment (public cloud / private cloud / linux / docker)
 *      ..
 *
 *
 *      DDD -> domain driven development
 *      event driven development
 *
 *
 *
 *      spring cloud
 */
