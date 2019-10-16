package com.phcarvalho.view.vo;

import com.phcarvalho.model.corba.FileMetadata;

public class FileMetadataAdapter {

    private FileMetadata fileMetadata;

    public FileMetadataAdapter(FileMetadata fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    public FileMetadata getFileMetadata() {
        return fileMetadata;
    }

    @Override
    public String toString() {
        return fileMetadata.name + " [" + fileMetadata.user.id + "]";
    }
}
