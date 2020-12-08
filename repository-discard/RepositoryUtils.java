package com.dahan.skate.repository;

import com.dahan.skate.Closes;
import com.dahan.skate.Langs;
import com.dahan.skate.collect.Lists;
import com.dahan.skate.collect.Sets;
import com.dahan.skate.collection.exception.DependencyNotObtained;
import com.dahan.skate.repository.dependency.Dependency;
import com.dahan.skate.repository.dependency.Scope;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 仓库工具类
 *
 * @author kevin
 */
public class RepositoryUtils
{

    private static final OkHttpClient mOkHttpClient = new OkHttpClient();

    private static final Logger logger = LoggerFactory.getLogger(RepositoryUtils.class);

    /**
     * 所有可用的仓库对象
     */
    private static final List<Repository> repositories = Lists.newArrayList();

    static
    {

        String[] repositoryAddresses = new String[]{
                "https://maven.aliyun.com/repository/central",
                "https://maven.aliyun.com/repository/public",
                "https://maven.aliyun.com/repository/public",
                "https://maven.aliyun.com/repository/google",
                "https://maven.aliyun.com/repository/gradle-plugin",
                "https://maven.aliyun.com/repository/spring",
                "https://maven.aliyun.com/repository/spring-plugin",
                "https://maven.aliyun.com/repository/grails-core",
                "https://maven.aliyun.com/repository/apache-snapshots",
        };

        for (String repositoryAddress : repositoryAddresses)
        {
            repositories.add(new Repository(repositoryAddress));
        }

    }

    /**
     * 所有下载失败的依赖坐标，避免重复下载
     */
    private static final Set<String> downloadFailure = Sets.newHashSet();

    /**
     * 下载依赖
     *
     * @param dependency 依赖信息
     */
    public static boolean downloadDependency(Dependency dependency, Repository repository, Dependency from)
    {
        String downloadPomUrl = repository.getDownloadAddress(dependency, Dependency.getPOM());
        String downloadJarUrl = repository.getDownloadAddress(dependency, Dependency.getJAR());
        // 只要能下载下来POM就算是成功的

        return download(downloadPomUrl, Repository.getLocalDirectory(), Dependency.getPOM(), dependency, from)
                || download(downloadJarUrl, Repository.getLocalDirectory(), Dependency.getJAR(), dependency, from);
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
            byte[] bytes = new byte[1024*4];
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

        Dependency dependency = null;

        // 当前下载依赖是否已经下载失败过，如果是的话则直接返回NULL。避免重复下载
        if (downloadFailure.contains(Dependency.getCoordinate(groupId, artifactId, version)))
        {
            return null;
        }

        for (Repository repository : repositories)
        {
            dependency = repository.getDependency(groupId, artifactId, version, scope, from, dm);
            if (dependency.isResolve())
            {
                break;
            }
        }

        if (dependency != null)
        {
            if (!dependency.isResolve())
            {
                downloadFailure.add(dependency.getCoordinate());
                logger.error(Langs.INFO_DOWNLOAD_FAILURE(dependency.getCoordinate(),
                        dependency.basePath(), from == null ? "ROOT" : from.getCoordinate()));
            }
        }

        return dependency;
    }

    public static Set<String> getDownloadFailure()
    {
        return downloadFailure;
    }
}
