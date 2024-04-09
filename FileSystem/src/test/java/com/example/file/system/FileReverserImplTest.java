package com.example.file.system;


import org.example.file.system.FileReverser;
import org.example.file.system.impl.FileReverserImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.*;

public class FileReverserImplTest {

    @Test
    public void testFileReversal() throws IOException {

        String inputContent = "abcdef";

        Reader readerMock = new StringReader(inputContent);
        Writer writerMock = Mockito.mock(Writer.class);

        FileReverser fileReverser = new FileReverserImpl(readerMock, writerMock);
        fileReverser.reverseFile();

        String expectedReversedContent = new StringBuilder(inputContent).reverse().toString();
        Mockito.verify(writerMock).write(expectedReversedContent);
    }

    @Test
    public void testNullReaderOrWriter() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FileReverserImpl(null, Mockito.mock(Writer.class));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new FileReverserImpl(Mockito.mock(Reader.class), null);
        });
    }

    @Test
    public void testClosingResources() throws IOException {
        Reader readerMock = Mockito.mock(Reader.class);
        Writer writerMock = Mockito.mock(Writer.class);

        FileReverser fileReverser = new FileReverserImpl(readerMock, writerMock);
        fileReverser.close();

        Mockito.verify(readerMock).close();
        Mockito.verify(writerMock).close();
    }

    @Test
    public void testEmptyFile() throws IOException {
        Reader readerMock = new StringReader("");
        Writer writerMock = Mockito.mock(Writer.class);

        FileReverser fileReverser = new FileReverserImpl(readerMock, writerMock);
        fileReverser.reverseFile();

        Mockito.verify(writerMock).write("");
    }
}