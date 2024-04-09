package org.example;

import org.example.file.system.FileReverser;
import org.example.file.system.impl.FileReverserImpl;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/dambodvab/Documents/GitHub/FileSystem/src/main/resources/input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/dambodvab/Documents/GitHub/FileSystem/src/main/resources/output.txt"))) {

            FileReverser fileReverser = new FileReverserImpl(reader, writer);
            fileReverser.reverseFile();
            System.out.println("File reversed successfully!");
            fileReverser.close();

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}