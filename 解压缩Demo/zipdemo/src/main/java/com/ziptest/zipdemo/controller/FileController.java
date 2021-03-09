package com.ziptest.zipdemo.controller;

import com.ziptest.zipdemo.common.ZIP4JUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipInputStream;

@RestController
public class FileController {
    
    @GetMapping("/test")
    public String test() {
        return "hello world";
    }
    
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) throws Exception {
        System.out.println(multipartFile);
        
        File file = null;
        InputStream ins = multipartFile.getInputStream();
        file=new File("D://"+multipartFile.getOriginalFilename());
        System.out.println("MultipartFile transform to File,MultipartFile name:"+multipartFile.getOriginalFilename());
        inputStreamToFile(ins, file);
        
        ZipFile zf = new ZipFile(file);
        ZipInputStream zin = new ZipInputStream(multipartFile.getInputStream());
        Enumeration entries = zf.getEntries();
        ZipEntry ze;
        while (entries.hasMoreElements()) {
            ze = (ZipEntry) entries.nextElement();
            if (ze.isDirectory()) {
            } else {
                System.err.println("file - " + ze.getName() + " : "
                        + ze.getSize() + " bytes");
                long size = ze.getSize();
                if (size > 0) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(zf.getInputStream(ze)));
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    br.close();
                }
                System.out.println();
            }
        }
        
//        String filename = multipartFile.getOriginalFilename();
//        ZIP4JUtils.readZipFileMD5ByZip4J(filename, null);
        return "success";
    }
    
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            System.out.println("MultipartFile transform to File completed!");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
