package com.imp.util.admin;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class FileUtile {

    public static String saveFile2Dir(MultipartFile file, String dirPath) {
        if (file == null) {
            return null;
        }
        try {
            File filePath = new File(dirPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String fileName = file.getOriginalFilename();
            String newFileName = System.currentTimeMillis() + "-" + uuid + fileName.substring(fileName.lastIndexOf("."));
            String lastName = dirPath + newFileName;
            File newFile = new File(lastName);
            byte[] bytes = file.getBytes();
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(newFile));
            buffStream.write(bytes);
            buffStream.close();
            return newFileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
