package com.dahan.gohan;

/*
 * Creates on 2020/12/3.
 */

/**
 * @author kevin
 */
public class MultiLanguage {

    public static final String INFO_DOWNLOAD_SUCCESS;

    public static final String INFO_DOWNLOAD_FAILURE;

    public static final String ERROR_DEPENDENCY_DOWNLOAD_FAILURE;

    public static final String ERROR_DEPENDENCY_CANNOT_IMPORT;

    static {
        INFO_DOWNLOAD_SUCCESS               = "文件下载成功，从: ";
        INFO_DOWNLOAD_FAILURE               = "文件下载失败，从: ";
        ERROR_DEPENDENCY_DOWNLOAD_FAILURE   = "依赖下载是失败";
        ERROR_DEPENDENCY_CANNOT_IMPORT      = "下载失败，请检查依赖坐标是否正确";
    }

}
