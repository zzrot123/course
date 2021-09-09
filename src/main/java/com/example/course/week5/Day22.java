package com.example.course.week5;


/**
 *  message queue
 *     queue
 *              producer1                       consumer1
 *              producer2    ->  [ queue ] ->   consumer2
 *              producer3                       consumer3
 *     pub - sub (observer)
 *                                          subscriber
 *              publish    ->  topic  ->    subscriber
 *                                          subscriber
 *
 *    aws SQS + SNS
 *      SQS : visibility timeout
 *      dead letter queue(rollback queue)
 *
 *    rabbit mq
 *          [   queue   ]
 *
 *    kafka (producer ack + consumer ack)
 *                          consumer 1 - m partitions
 *                          [ partition 1]    -> consumer1(group1) , consumer1(group2)
 *                          [ partition 2]    -> consumer1(group1) , consumer1(group2)
 *                          [ partition 3]    -> consumer2(group1) , consumer2(group2)
 *                          [ partition 4]    -> consumer2(group1) , consumer3(group2)
 *                          consumer group 1 (consumer1, consumer2)
 *                          consumer group 2 (consumer1, consumer2, consumer3)
 *
 *
 *                          [ partition 1]    -> consumer1(group1)
 *                          [ partition 2]    -> consumer1(group1)
 *                          [ partition 3]    -> consumer2(group1)
 *                          [ partition 4]    -> consumer2(group1)
 *                          consumer group 1 (consumer1, consumer2)
 *
 *
 *          broker1 [partition 1 master]
 *                  [partition 2 slave]
 *
 *          broker2 [partition 1 slave]
 *                  [partition 2 master]
 *
 *          zookeeper
 *
 *   -------------------------------------------------------------------------
 *   producer
 *        1. save to database (tx)
 *           fail
 *        2. send msg to kafka (tx)
 *
 *        1. send msg to kafka (tx)
 *           fail
 *        2. save to database (tx)
 *
 *  CDC (change data capture)
 *       database
 *            student table
 *            session / message table
 *       1. insert message into session / message table
 *          insert data into student table
 *       2. commit tx
 *          fail
 *       3. send response
 *
 *
 *       another server  ->  message into session / message table
 *                       ->  send message to message queue
 *                           fail
 *                       ->  remove message from table
 *  -------------- *  -------------- *  -------------- *  -------------- *  --------------
 *
 *    SQS SNS
 *    S3 (version)
 *    VPC
 *    application load balancer
 *    ASG -> EC2
 *    route53
 *    api gateway
 *    -----------
 *    ECS + ECR(docker repository)
 *    task definition
 *          docker location
 *          docker container
 *          cpu  / memory..
 *    EC2(agent)
 *    fargate(serverless)
 *
 *  -----------------------------------------------------------------------------------
 *  cluster vs non-cluster
 *  b+tree index vs bitmap index
 *  execution plan + hint
 *  pros + cons of index
 *
 *
 *  select
 *  from
 *  where
 *  group by
 *  having
 *
 *
 */