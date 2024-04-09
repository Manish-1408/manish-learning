package org.example.file.system.impl;

import org.example.file.system.FileReverser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Objects;

public class FileReverserImpl implements FileReverser {

    private final Reader reader;
    private final Writer writer;

    public FileReverserImpl(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;

        if (reader == null || writer == null) {
            throw new IllegalArgumentException("Reader and Writer cannot be null");
        }
    }

    public void reverseFile() throws IOException {
        StringBuilder content = readContentFromFile();
        content.reverse();
        writeContentToFile(content);
    }

    private StringBuilder readContentFromFile() throws IOException {
        StringBuilder content = new StringBuilder();
        int data;
        while ((data = reader.read()) != -1) {
            content.append((char) data);
        }
        return content;
    }

    private void writeContentToFile(StringBuilder content) throws IOException {
        writer.write(content.toString());
    }

    public void close() throws IOException {
        close(reader);
        close(writer);
    }

    private void close(Closeable resource) throws IOException {
        if (resource != null) {
            resource.close();
        }
    }
}
