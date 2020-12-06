package com.dahan.gohan.repository;

import com.dahan.gohan.Closes;
import com.dahan.gohan.Langs;
import com.dahan.gohan.collection.exception.DependencyNotObtained;
import com.dahan.gohan.repository.dependency.Dependency;
import com.dahan.gohan.repository.dependency.Scope;
import com.dahan.gohan.repository.initialize.alibaba.AlibabaCenter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Objects;

/**
 * 仓库工具类
 *
 * @author kevin
 */
public class RepositoryUtils
{

    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    private static final Logger logger = LoggerFactory.getLogger(RepositoryUtils.class);

    private static AlibabaCenter maven = new AlibabaCenter();

    /**
     * 下载依赖
     *
     * @param dependency 依赖信息
     */
    public static void downloadDependency(Dependency dependency, Repository repository, Dependency from)
    {
        if (repository.getType() == Repository.getREMOTE())
        {
            String downloadPomUrl = repository.getDownloadAddress(dependency, Dependency.getPOM());
            String downloadJarUrl = repository.getDownloadAddress(dependency, Dependency.getJAR());
            // 只要能下载下来POM就算是成功的
            if (download(downloadPomUrl, Repository.getLocalDirectory(), Dependency.getPOM(), dependency, from))
            {
                download(downloadJarUrl, Repository.getLocalDirectory(), Dependency.getJAR(), dependency, from);
            }

        }

    }

    /**
     * 文件下载
     *
     * @param url       下载地址
     * @param directory 存储目录
     */
    @SuppressWarnings("ConstantConditions")
    public static boolean download(String url, String directory, int type, Dependency dependency, Dependency from)
    {
        try
        {
            Request request = new Request.Builder().url(url).build();
            Response response = mOkHttpClient.newCall(request).execute();
            if (response.isSuccessful())
            {
                String filename = (type == Dependency.getJAR() ? dependency.jar() : dependency.pom());
                writeFile(response, directory, type, filename, url, dependency, from);
                response.body().close();
                return true;
            } else
            {
                Repository.getCollects().push(new DependencyNotObtained(dependency, type, url));
                logger.error(Langs.INFO_DOWNLOAD_FAILURE(dependency.getCoordinate(), url, from == null ? "ROOT" : from.getCoordinate()));
                response.body().close();
                return false;
            }
        } catch (Throwable e)
        {
            e.printStackTrace();
            return false;
        }
    }

    private static void writeFile(Response response, String directory, int type, String filename, String url,
                                  Dependency dependency, Dependency from) throws IOException
    {
        InputStream is = null;
        FileOutputStream fos = null;
        if (DependencyUtils.isJar(type))
        {
            is = Objects.requireNonNull(response.body()).byteStream();
        } else
        {
            String xml = Objects.requireNonNull(response.body()).string();
            is = new ByteArrayInputStream(xml.getBytes());
        }

        File file = new File(directory, filename);
        try
        {
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int len;
            //获取下载的文件的大小
            while ((len = is.read(bytes, 0, bytes.length)) != -1)
            {
                fos.write(bytes, 0, len);
            }

            logger.info(Langs.INFO_DOWNLOAD_SUCCESS(dependency.getCoordinate(), url,
                    from == null ? "ROOT" : from.getCoordinate(), file.getPath()));
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            Closes.doClose(is, fos);
        }

    }

    public static Dependency getDependency(String g, String a, String v)
    {
        return getDependency(g, a, v, null, null, false);
    }

    public static Dependency getDependency(String g, String a, String v, Dependency from)
    {
        return getDependency(g, a, v, null, from, false);
    }

    public static Dependency getDependency(String g, String a, String v, boolean dm)
    {
        return getDependency(g, a, v, null, null, dm);
    }

    public static Dependency getDependency(String groupId, String artifactId, String version, Scope scope, Dependency from, boolean dm)
    {
        return maven.getDependency(groupId, artifactId, version, scope, from, dm);
    }

    public static AlibabaCenter getMaven()
    {
        return maven;
    }

    public static void setMaven(AlibabaCenter maven)
    {
        RepositoryUtils.maven = maven;
    }

}
