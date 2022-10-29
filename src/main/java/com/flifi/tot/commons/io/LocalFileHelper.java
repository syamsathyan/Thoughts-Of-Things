package com.flifi.tot.commons.io;

import com.flifi.tot.commons.util.BaseGUIDGenerator;
import com.flifi.tot.commons.util.DateUtils;
import com.flifi.tot.commons.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by sathyasy on 8/28/15.
 */
public class LocalFileHelper {

    /**
     * Use for base + yearmonth + file name + extension pattern
     *
     * @param guidLength (length of the Base 62 guid file name)
     * @return filePath (home/1401/Yde0uFCvqz.jpg)
     */
    public static Path createYearMonth_Base62Path(String basepath, int guidLength, String extension) {
        String fileName = BaseGUIDGenerator.GetBase62(guidLength);
        String justFilePath = StringUtils.joinFast(basepath, StringUtils.FILE_SEPERATOR, DateUtils.getCurrentYearMonth(), StringUtils.FILE_SEPERATOR, fileName, extension);
        return Paths.get(justFilePath); // return absolute path
    }

    /**
     * Use for base + yearmonthday + file name + extension pattern
     *
     * @param guidLength (length of the Base 62 guid file name)
     * @return filePath (home/140101/Yde0uFCvqz.jpg)
     */
    public static Path createYearMonthDay_Base62Path(String basepath, int guidLength, String extension) {
        String fileName = BaseGUIDGenerator.GetBase62(guidLength);
        String justFilePath = StringUtils.joinFast(basepath, StringUtils.FILE_SEPERATOR, DateUtils.getCurrentYearMonthDay(), StringUtils.FILE_SEPERATOR, fileName, extension);
        return Paths.get(justFilePath); // return absolute path
    }

    /**
     * @param content
     * @param completeFilePath
     * @param options
     * @return size of the created file in bytes
     * @throws IOException
     */
    public static long saveFile(InputStream content, Path completeFilePath, CopyOption... options) throws IOException {
        //Create Directories
        Files.createDirectories(completeFilePath.getParent());
        //Path absoluteFilePath = Files.createFile(completeFilePath).toAbsolutePath();
        Path absoluteFilePath = completeFilePath.toAbsolutePath();
        //Copy the file to Disk and return size
        return Files.copy(content, absoluteFilePath, options);
    }

    /**
     * @param content
     * @param completeFilePath
     * @param options
     * @return absolute path to the created file
     * @throws IOException
     */
    public static String saveFile2(InputStream content, Path completeFilePath, CopyOption... options) throws IOException {
        //Create Directories
        Files.createDirectories(completeFilePath.getParent());
        //Path absoluteFilePath = Files.createFile(completeFilePath).toAbsolutePath();
        Path absoluteFilePath = completeFilePath.toAbsolutePath();
        //Copy the file to Disk and return size
        Files.copy(content, absoluteFilePath, options);
        return absoluteFilePath.toString();
    }
}
