package com.dahan.gohan.repository

import com.dahan.gohan.Closes
import com.dahan.gohan.MultiLanguage
import com.dahan.gohan.UrlTools
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
class RepositoryUtils
{

    private static final OkHttpClient mOkHttpClient = new OkHttpClient()

    private static final Logger logger = LoggerFactory.getLogger(RepositoryUtils.class)

    /**
     * 下载依赖
     * @param dependency 依赖信息
     */
    static void downloadDependency(Dependency dependency, Repository repository)
    {
        if (repository.getType() == Repository.REMOTE)
        {
            def downloadPomUrl = repository.getDownloadAddress(dependency, Dependency.POM)
            def downloadJarUrl = repository.getDownloadAddress(dependency, Dependency.JAR)
            // 只要能下载下来POM就算是成功的
            if (download(downloadPomUrl, repository.localDirectory, Dependency.POM, dependency))
            {
                download(downloadJarUrl, repository.localDirectory, Dependency.JAR, dependency)
                DependencyUtils.matchChildrenDependency(dependency)
            }
        }
    }

    static boolean download(String url, String directory, int type, Dependency dependency)
    {
        download(url, directory, type, dependency, true)
    }

    /**
     * 文件下载
     *
     * @param url 下载地址
     * @param directory 存储目录
     * @param filename 文件名
     */
    static boolean download(String url, String directory, int type, Dependency dependency, boolean debug)
    {
        Request request = new Request.Builder().url(url).build()
        def response = mOkHttpClient.newCall(request).execute()
        if (response.isSuccessful())
        {
            String filename = (type == Dependency.JAR ? dependency.jar() : dependency.pom())
            writeFile(response, directory, type, filename)
            if (debug) logger.info(MultiLanguage.INFO_DOWNLOAD_SUCCESS.concat(url))
            return true
        } else
        {
            Repository.collects.push(new DependencyNotObtained(dependency, type, url))
            if (debug) logger.error(MultiLanguage.INFO_DOWNLOAD_FAILURE.concat(url))
            return false
        }
    }

    private static void writeFile(Response response, String directory, int type, String filename)
    {
        InputStream is = null
        FileOutputStream fos = null
        if (DependencyUtils.isJar(type))
        {
            is = Objects.requireNonNull(response.body()).byteStream()
        } else
        {
            def xml = response.body().string()
            is = new ByteArrayInputStream(xml.bytes)
        }
        File file = new File(directory, filename)
        try
        {
            fos = new FileOutputStream(file)
            byte[] bytes = new byte[1024]
            int len
            //获取下载的文件的大小
            while ((len = is.read(bytes, 0, bytes.length)) != -1)
            {
                fos.write(bytes, 0, len)
            }
        } catch (Exception e)
        {
            e.printStackTrace()
        } finally
        {
            Closes.doClose(is, fos)
        }
    }

}
