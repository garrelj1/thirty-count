package com.garrell.co.baseapp.common.files;

import android.content.Context;

import java.io.File;

public class FilesDirectoryManager {

    private final File filesDir;

    public FilesDirectoryManager(Context context) {
        filesDir = context.getFilesDir();
    }

    public File getFilesDir() {
        return filesDir;
    }

}
