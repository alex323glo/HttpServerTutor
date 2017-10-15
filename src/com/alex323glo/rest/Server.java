package com.alex323glo.rest;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by alex323glo on 14.10.17.
 */
public class Server {

    public static void startServer() throws IOException {
        // create http server (listens to port:8000):
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

        // add handlers:
        httpServer.createContext("/test", new TestHandler());
        httpServer.createContext("/static", new StaticFileHandler());

        // run server:
        httpServer.setExecutor(null);
        httpServer.start();
        System.out.println("HTTP server started.");
    }

}
