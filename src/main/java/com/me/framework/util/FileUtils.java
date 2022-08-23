package com.me.framework.util;

import com.me.framework.common.BusinessException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/***
 * @author yg
 * @date 2022/8/22 17:07
 * @version 1.0
 */
public class FileUtils {

    /**
     * 移动目录并替换已存在的文件
     * @param source
     * @param target
     * @throws IOException
     */
    public static void moveDirectorAndReplaceExistsFile(File source,File target) throws IOException {
        if(!source.exists()){
            throw new BusinessException("MOVE源目录不存在");
        }
        if(!source.isDirectory()){
            throw new BusinessException("MOVE源目录不是文件夹");
        }
        if(!target.exists()){
            Files.move(source.toPath(),target.toPath(), StandardCopyOption.ATOMIC_MOVE);
        }
        File [] files = null;
        if((files = source.listFiles()) != null){
            for (File file : files) {
                File targetFile = new File(target,file.getName());
                if(file.isDirectory()){
                    moveDirectorAndReplaceExistsFile(file,targetFile);
                }else{
                    Files.move(file.toPath(),targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
        org.apache.commons.io.FileUtils.deleteDirectory(source);
    }

    /**
     * 清除windows文件名特殊字符
     * @param name
     * @return
     */
    public static String clearWindowsFileSpecialCharacter(String name){
        return name.replaceAll("[\\\\\\/:\\*\\?\"<>\\|]","");
    }
}
