package com.example.course.week4;

import java.nio.charset.StandardCharsets;

public class Day18 {

    public static void main(String[] args) {
        char ch1 = 98;
        char ch2 = 99;
        char ch3 = 138;
        byte[] b1 = new byte[]{1, 2, 3, 4};
        System.out.println(new String(b1, StandardCharsets.UTF_8));
        System.out.println(new String(b1, StandardCharsets.UTF_16));
    }
}

/**
 *   encoding vs encryption
 *
 *   keep website / rest api secured
 *   1) encryption:
 *      https = SSL/TLS(layer6) + http
 *
 *   1. without certificate
 *   -> www.google.com(im real)
 *   -> www.gogle.com(im real) -> login -> username + password -> www.gogle.com
 *
 *
 *   2. with certificate
 *   -> www.google.com
 *      public key <-> private key
 *      me                              server
 *              hi ->
 *              hi <-
 *         public key[random String] ->
 *         <- private key[hash string]
 *         public key[symmetric key] ->
 *      symmetric key[username + password] ->
 *
 *   2) authentication
 *     user identity
 *         * username password
 *         * text message / phone number
 *         * email address
 *   3) authorization
 *      json web token {user basic info, token, expire time, refresh, role}
 *      encode(header.payload.signature)
 *                           .encrypt(header.payload)
 *
 *      OAuth2.0
 *   4) verify user input (sql injection / xml injection)
 *   5) ip address to mark last login location
 *   6) encrypt data / hash data
 *   7) CSRF
 *      login chrome (token stored in cookie)
 *          www.bank.com/transfer/from=your account&to=your friend
 *      click picture -> endpoint
 *          www.bank.com/transfer/from=your account&to=their account
 *
 *   Authentication
 *   1) filter
 *   2) UsernamePasswordAuthenticationFilter
 *   3) Provider(DaoAuthenticationProvide)
 *   4) compare user from request and user from UserDetailsService
 *
 *   Authorization
 *   1) add filter
 *   2) read header
 *   3) verify header token with jwt utilities
 *   4) if verified -> add user info into thread local(security context)
 *      else -> verify username + password
 *   5) clean up thread local
 *
 *
 *    tomorrow
 *    1:00pm CDT
 *    2:00pm EDT
 *
 *    rest api practice
 *
 *
 *
 *   build spring boot application
 *      1. call 3rd party weather api , and get result , display in your response
 *         use RestTemplate to send request to 3rd party and get response
 *      2. call multiple 3rd party weather apis at same time
 *         use thread pool
 *         use CompletableFuture to send requests at same time
 *      3. build local cache to cache some frequently used data
 *         use concurrent hashmap
 *         build singleton concurrent hashmap
 *      4. authentication + authorization
 *      5. build a gateway(zuul / spring cloud gateway)
 *
 */
