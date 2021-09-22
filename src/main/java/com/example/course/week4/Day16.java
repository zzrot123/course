package com.example.course.week4;

public class Day16 {
}

/**
 *      server vs client?
 *      chrome(client) -> www.google.com -> request -> server1
 *
 *
 *   (*  )chrome(client)
 *          -> www.google.com
 *          -> network card(mac address)
 *          -> use mac address to exchange private ip from Wifi
 *          -> private ip : port match with public ip : port from NAT in route table
 *          -> DNS(domain name - ip address(IPV4 range 0.0.0.0 - 255.255.255.255))
 *          -> get target public ip address
 *          -> http request to public ip address
 *             Application layer : user interface HTTP(data)
 *             Presentation layer : SSH / SSL encryption
 *             Session  layer : socket
 *             Transport layer : tcp header(port) + data
 *                     person1       hi I want to build connection   ->     person2     Syn=1, Seq=x (person1 's seq number)
 *                     person1     <- OK let me build connection with you   person2     Syn=1, Seq=y (person2 's seq number), ack=x+1
 *                     person1              received      ->                person2     ack=y+1
 *
 *                     person1             data package len=500 ->          person2     seq=x+1, len=500
 *                     person1             <-     received it               person1     ack=x+1+500=x+501..
 *
 *                     person1          hi i want to disconnect     ->      person2
 *                     person1          <-    just wait a sec               person2
 *                     person1          <- ok we can disconnect now         person2
 *                     person1              received it         ->          person2
 *
 *             Network layer  :  ip header(ip address) + tcp header + data
 *             Data link layer : mac address + ip + tcp header + data
 *             Physical layer : cable
 *          -> server socket accept() client connection[client public ip address : port, server public ip address : port]
 *             while(hasConnection blocking function) {
 *                  assign connection to another thread :
 *                      read input stream
 *                      verify certificate + exchange encryption key => SSL/TLS
 *                      handle the request based on get  + /students => controller / servlet
 *             }
 *             server will handle your request
 *             generate response back to you
 *          -> send response to client public ip:port
 *
 *
 *
 *          layer 7: http content
 *              -> layer 6 : encrypt http content (SSL)
 *                  -> layer 5 : socket wrap info and try to send request
 *                      -> layer 4 : socket choose TCP as connection protocol and add port / tcp info in package
 *                          -> layer 3 : put IP info in package (source IP Address(exchanges mac address with private ip from DHCP, router will re-write it later ???) + destination IP Address from DNS)
 *                              -> layer 2 : add network card / mac address (my mac address + router mac address) within ethernet network (private environment), send request from private to public
 *                                  -> layer 1 : cable (public environment)
 *                                          ....
 *                                  -> layer 1 : cable (public environment)
 *                              -> layer 2 : find server location in ethernet network (private environment)
 *                          -> layer 3 : parse IP info (IP Address)
 *                      -> layer 4 : parse TCP info (SYN / ACK / SEQ)
 *                 -> layer 5 : socket : parse input stream verify tcp header (data or close or build connection)
 *             -> layer 6 : decrypt / key exchange (SSL)
 *         layer 7 : decoding http content
 *
 * ------------------------------------------------------------------------------------------------------
 *          Session vs Cookie
 *
 *          send request(sessionId) -> server
 *              1. server check your header(sessionId)
 *              2. if sessionId
 *                      read your data
 *                 else
 *                      session(sessionId)
 *              3. send back with sessionId(client will store sessionId in cookie)
 *
 *              LB(Sticky session)
 *          /   |   \
 *         N1   N2  N3
 *
 *              LB
 *          /   |   \
 *         N1   N2  N3
 *         \    |   /
 *      global cache/database
 *
 *
 *
 *      Rest Api
 *          1. stateless
 *          2. http
 *              method: get / post / delete / put / patch / head...
 *                      get : retrieve data
 *                      post: create new resource
 *                      put : update (whole resource)   idempotent
 *                      patch:update (partial update)   idempotent
 *                            update stu123's firstname to "Tom"
 *                      head:
 *              status code: 200 OK, 201 Created, 204 OK with no Content
 *                           400 Bad Request, 401 un-authenticated, 403 un-authorized, 404 not found
 *                           500 internal server error
 *              header :
 *                      content-type : tell server, the request body type
 *                      accept : type from server to client
 *              endpoint / uri / url => /noun
 *                 students
 *                 get all students
 *                      endpoint: /students?age>10
 *                      http method: get
 *                      status code: 200 / 500
 *                      response body :
 *                          {
 *                             pageNumber: 1,
 *                             pageCount : 10,
 *                             totalCount: 100,
 *                             timestamp : "..",
 *                             businessCode : ".."
 *                             data: [
 *                                      {
 *                                          "id" : 1,
 *                                          "name" : xx
 *                                      },
 *                                      {
 *                                          "id" : 2,
 *                                          "name" : xx
 *                                      }
 *                                  ]
 *                          }
 *                 create student
 *                      endpoint: /students?name=Tom
 *                      http method: post
 *                      status code: 201 / 500 / 400
 *                      request body:
 *                          {
 *                              "name": xx
 *                          }
 *                      response body:
 *                          {
 *                               "id": 3
 *                          }
 *                 get student by id
 *                      endpoint: /student/{id}  path variable
 *                      http method: get
 *                      status code: 200 / 400 / 500 / 404
 *                 delete student by id
 *                      endpoint:
 *                      http method: delete
 *                      status code:
 *
 *
 *                 error response
 *                 status 500
 *                 {
 *                     "message" : ..,
 *                     "timestamp" : ..
 *                 }
 */
