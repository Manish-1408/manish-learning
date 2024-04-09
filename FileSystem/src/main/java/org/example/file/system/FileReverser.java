package org.example.file.system;

import java.io.Closeable;
import java.io.IOException;

public interface FileReverser extends Closeable {
    void reverseFile() throws IOException;
}
