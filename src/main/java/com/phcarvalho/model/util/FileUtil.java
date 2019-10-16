package com.phcarvalho.model.util;

import com.phcarvalho.model.configuration.Configuration;
import com.phcarvalho.model.corba.FileData;
import com.phcarvalho.model.corba.FileMetadata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FileUtil {

    public static FileData readLocalFile(FileMetadata fileMetadata) {
        String sharedDirectoryPath = Configuration.getSingleton().getSharedDirectoryPath();
        Path path = Paths.get(sharedDirectoryPath + "\\" + fileMetadata.name);

        try {
            byte[] byteArray = Files.readAllBytes(path);
            String content = Base64.getEncoder().encodeToString(byteArray);

            return new FileData(fileMetadata, content);
        } catch (IOException e) {
            e.printStackTrace();

            throw new RuntimeException("readLocalFile");
        }
    }
}
