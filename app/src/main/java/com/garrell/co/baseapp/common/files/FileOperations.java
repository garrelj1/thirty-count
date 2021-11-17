package com.garrell.co.baseapp.common.files;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileOperations {

    public static void copyBytes(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

}
