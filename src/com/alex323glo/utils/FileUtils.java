package com.alex323glo.utils;

import java.io.*;

/**
 * Created by alex323glo on 14.10.17.
 */
public class FileUtils {

    public static String read(String filePath) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        StringBuilder result = new StringBuilder();

        while (bufferedReader.ready()) {
            result.append(bufferedReader.readLine());
        }

        return result.toString();
    }

    public static void write(String[] text, String filePath) {
        if (text == null) {
            throw new NullPointerException("Input text is null!");
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            for (String line: text) {
                bufferedWriter.write(line);
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("system: Can't write to file \"" + filePath + "\" !");
            e.printStackTrace();
        }

    }
}
