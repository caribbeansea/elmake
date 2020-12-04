package com.dahan.gohan.repository

import com.dahan.gohan.MultiLanguage
import com.dahan.gohan.collection.exception.DependencyNotObtained
import com.dahan.gohan.http.Areyouok
import com.dahan.gohan.repository.dependency.Dependency
import com.dahan.gohan.repository.initialize.alibaba.MavenOfAlibaba
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/*
 * Creates on 2020/12/3.
 */

/**
 * 仓库工具类
 * @author kevin
 */
class RepositoryUtils {

    private static final OkHttpClient mOkHttpClient = new OkHttpClient()

    private static final Logger logger = LoggerFactory.getLogger(RepositoryUtils.class)

    /**
     * 下载依赖
     * @param dependency 依赖信息
     */
    static void downloadDependency(Dependency dependency, Repository repository) {
        if (repository.getType() == Repository.REMOTE) {
            def downloadPomUrl = repository.getDownloadAddress(dependency, Dependency.POM)
            def downloadJarUrl = repository.getDownloadAddress(dependency, Dependency.JAR)
            // 每个包必须包含POM和JAR否则就算下载失败
            download(downloadPomUrl, repository.localDirectory, Dependency.POM, dependency)
            download(downloadJarUrl, repository.localDirectory, Dependency.JAR, dependency)
        }
    }

    /**
     * 文件下载
     *
     * @param url 下载地址
     * @param directory 存储目录
     * @param filename 文件名
     */
    static void download(String url, String directory, int type, Dependency dependency) {
        Request request = new Request.Builder().url(url).build();
        def response = mOkHttpClient.newCall(request).execute()
        if (response.isSuccessful()) {
            String filename = (type == Dependency.JAR ? dependency.jar() : dependency.pom())
            writeFile(response, directory, filename)
            logger.info(MultiLanguage.INFO_DOWNLOAD_SUCCESS.concat(url))
        } else {
            Repository.collects.push(new DependencyNotObtained(dependency, type, url))
            logger.error(MultiLanguage.INFO_DOWNLOAD_FAILURE.concat(url))
        }
    }

    private static void writeFile(Response response, String directory, String filename) {
        FileOutputStream fos = null;
        InputStream is = Objects.requireNonNull(response.body()).byteStream();
        File file = new File(directory, filename);
        try {
            fos = new FileOutputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void main(String[] args) {
        def r = new MavenOfAlibaba()
        def d = r.getDependency("com.alibaba", "fastjson", "1.2.6611")
        Repository.collects.rollout()
    }

}
