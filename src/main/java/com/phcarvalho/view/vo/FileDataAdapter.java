package com.phcarvalho.view.vo;

import com.phcarvalho.model.corba.FileData;

public class FileDataAdapter {

    private FileData fileData;

    public FileDataAdapter(FileData fileData) {
        this.fileData = fileData;
    }

    public FileData getFileData() {
        return fileData;
    }

    @Override
    public String toString() {
        return fileData.metadata.name + " [" + fileData.metadata.user.id + "]";
    }
}
