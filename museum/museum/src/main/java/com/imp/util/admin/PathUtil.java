package com.imp.util.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.File;


@Slf4j
public class PathUtil {

    private static final String SEPARATOR = "/";

    /**
     * 项目文件主目录
     */
    private static String FILE_ROOT_PATH;

    /**
     * 用户头像
     */
    public static final PathUtil USER_PHOTO = new PathUtil("userPhoto");

    /**
     * 视频区-上传视频接
     */
    public static final PathUtil VIDEO = new PathUtil("video");

    /**
     * 音频路径
     */
    public static final PathUtil AUDIO = new PathUtil("audio");



    /**
     * 路径
     */
    private String path;

    public PathUtil() {
    }

    public PathUtil(String path) {
        if (StringUtils.isEmpty(path)) {
            return;
        }
        if (path.endsWith(SEPARATOR)) {
            this.path = path;
        } else {
            this.path = path + SEPARATOR;
        }
    }

    public static String getFileRootPath() {
        return FILE_ROOT_PATH;
    }

    public static void setFileRootPath(String fileRootPath) {

        if (fileRootPath.endsWith(SEPARATOR)) {
            FILE_ROOT_PATH = fileRootPath;
        } else {
            FILE_ROOT_PATH = fileRootPath + SEPARATOR;
        }
    }

    /**
     * 磁盘路径
     */
    public String getDiskPath() {

        String diskPath = FILE_ROOT_PATH + path;
        File diskPathFile = new File(diskPath);
        if (!diskPathFile.exists() && !diskPathFile.mkdirs()) {
            log.error("创建目录失败[{}]", diskPath);
        }

        return diskPath;
    }

    /**
     * url路径
     */
    public String getUrlPath() {
        return path;
    }


}
