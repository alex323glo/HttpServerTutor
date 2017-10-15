package com.alex323glo;

import com.alex323glo.rest.Server;
import com.alex323glo.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by alex323glo on 14.10.17.
 */
public class Run {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Server.startServer();
        } catch (IOException e) {
            System.out.println("Server failed.");
            e.printStackTrace();
        }



        String command = "";
        while (true) {
            System.out.println("system: ");
            command = scanner.next();

            switch (command) {
                case "exit":
                    System.exit(0);
                case "js":
                    System.out.println("\n~Input JavaScript code (put line \"~\" to finish writing):");
                    ArrayList<String> textJS = new ArrayList<>();
                    String input = "";
                    while (!input.equals("~")) {
                        textJS.add(input);
                        input = scanner.nextLine();
//                        System.out.println("    (inputed: " + input + ")");
                    }
                    String[] textJSArray = new String[textJS.size()];
                    textJS.toArray(textJSArray);
                    FileUtils.write(textJSArray, "resources/static/script.js");
            }
        }

    }

}
