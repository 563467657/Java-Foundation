package com.ziptest.zipdemo.common;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;

import java.nio.charset.Charset;
import java.util.List;

public class ZIP4JUtils {
    /**
     * @param file
     * @throws Exception
     */
    public static void readZipFileMD5ByZip4J(String file, String passwd) throws Exception {
        
        file.replaceAll("\\\\", "/");
        ZipFile zFile = new ZipFile(file);
        // 此处最好立即设置字符集
        zFile.setCharset(Charset.forName("GBK"));
        if (!zFile.isValidZipFile()) {
            return;
        }
        if (zFile.isEncrypted()) {
            zFile.setPassword(passwd.toCharArray());
        }
        
        
        // 获取ZIP中所有文件的FileHeader，以便后面对zip中文件进行遍历
        List<FileHeader> list = zFile.getFileHeaders();
        // 此时list的size包括：文件夹、子文件夹、文件的个数
        System.out.println(list.size());
        // 遍历其中的文件
        for (FileHeader fileHeader : list) {
            String fileName = fileHeader.getFileName();
            // fileName会将目录单独读出来，而且带有路径分割符
            
            if (fileHeader.isDirectory()) {
                //if (fileName.endsWith("/") || fileName.endsWith("\\\\") || fileName.endsWith("\\")) {
                System.out.println(fileName + " 这是一个文件夹。");
                continue;
            } else {
                ZipInputStream inputStream = zFile.getInputStream(fileHeader);
                //下面就可以获取或者保存ZipInputStream，转换为标准InputStream后获取文件内容。
                //String Md5String = BigFileMD5.getStreamMD5(inputStream);
                System.out.println(fileName + " 这是一个文件，该文件的MD5值：" + fileHeader.getFileName());
            }
        }
    }
}
