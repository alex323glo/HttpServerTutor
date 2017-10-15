package com.alex323glo.rest;

import com.alex323glo.utils.FileUtils;
import com.alex323glo.utils.UrlUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by alex323glo on 14.10.17.
 */
public class StaticFileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("_______________________________________");
        System.out.println("StaticFileHandler: request was handled:");

        // print request info:
        System.out.println(" * Request method: " + httpExchange.getRequestMethod());
        System.out.println(" * Request URI : " + httpExchange.getRequestURI().toString());
        System.out.println(" * Protocol: " + httpExchange.getProtocol());

        // get file name from request url:
        String[] urlParts = UrlUtils.parseURL(httpExchange.getRequestURI().toString());
        String filePath = urlParts[urlParts.length - 1];

        // search for such static file:
        File searchedFile = new File("resources/static/" + filePath);

        // return error response (user error) if no such file was founded:
        if (!searchedFile.isFile() || !searchedFile.exists()) {
            String response = "No such file in resources (400)!";

            httpExchange.sendResponseHeaders(400, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

            System.out.println(response);
            System.out.println("---------------------------------------");
            System.out.println("system: ");
            return;
        }

        // try to read file from resources ("resources/static/");
        String responseBody;
        try {
            responseBody = FileUtils.read(searchedFile.getPath());
        } catch (IOException e) {   // return error response (server error) if can't read needed static file:
            e.printStackTrace();
            String response = "Can't read file from resources (500)!";

            httpExchange.sendResponseHeaders(500, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

            System.out.println(response);
            System.out.println("---------------------------------------");
            System.out.println("system: ");
            return;
        }

        // send response header:
        httpExchange.sendResponseHeaders(200, responseBody.length());

        // send response body using OutputStream:
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(responseBody.getBytes());
        outputStream.close();

        System.out.println("StaticFileHandler: Static file \n\n\t\"" + searchedFile.getPath() + "\"\n\nwas sent.");
        System.out.println("---------------------------------------");
        System.out.println("system: ");
    }
}
