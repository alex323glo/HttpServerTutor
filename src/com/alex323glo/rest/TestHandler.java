package com.alex323glo.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Test HTTP handler, which handles "/test" URL.
 *
 * Created by alex323glo on 14.10.17.
 */
public class TestHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("_______________________________________");
        System.out.println("TestHandler: request was handled:");

        // print request info:
        System.out.println(" * Request method: " + httpExchange.getRequestMethod());
        System.out.println(" * Request URI : " + httpExchange.getRequestURI().toString());
        System.out.println(" * Protocol: " + httpExchange.getProtocol());


        // create response body:
        StringBuilder responseBody = new StringBuilder("Test response body string.");

        // send response header:
        httpExchange.sendResponseHeaders(200, responseBody.length());

        // send response body using OutputStream:
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(responseBody.toString().getBytes());
        outputStream.close();

        System.out.println("TestHandler: test response was sent.");
        System.out.println("---------------------------------------");
        System.out.println("system: ");
    }
}
