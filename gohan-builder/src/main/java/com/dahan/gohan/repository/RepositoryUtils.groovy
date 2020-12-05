package com.dahan.gohan.repository

import com.dahan.gohan.Closes
import com.dahan.gohan.collection.exception.DependencyNotObtained
import com.dahan.gohan.repository.dependency.Dependency
import com.dahan.gohan.repository.dependency.Scope
import com.dahan.gohan.repository.initialize.alibaba.MavenOfAlibaba
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import static com.dahan.gohan.Langs.*

/* ************************************************************************
 *
 * Copyright (C) 2020 2B键盘 All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

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
    static void downloadDependency(Dependency dependency, Repository repository, Dependency from)
    {
        if (repository.getType() == Repository.REMOTE)
        {
            def downloadPomUrl = repository.getDownloadAddress(dependency, Dependency.POM)
            def downloadJarUrl = repository.getDownloadAddress(dependency, Dependency.JAR)
            // 只要能下载下来POM就算是成功的
            if (download(downloadPomUrl, repository.localDirectory, Dependency.POM, dependency, from))
            {
                download(downloadJarUrl, repository.localDirectory, Dependency.JAR, dependency, from)
                DependencyUtils.matchChildrenDependency(dependency)
            }
        }
    }

    /**
     * 文件下载
     *
     * @param url 下载地址
     * @param directory 存储目录
     * @param filename 文件名
     */
    static boolean download(String url, String directory, int type, Dependency dependency, Dependency from)
    {
        Request request = new Request.Builder().url(url).build()
        def response = mOkHttpClient.newCall(request).execute()
        if (response.isSuccessful())
        {
            String filename = (type == Dependency.JAR ? dependency.jar() : dependency.pom())
            writeFile(response, directory, type, filename, url, dependency, from)
            response.body().close()
            return true
        } else
        {
            Repository.collects.push(new DependencyNotObtained(dependency, type, url))
            logger.error(INFO_DOWNLOAD_FAILURE(dependency.getCoordinate(), url, from == null ? "ROOT" : from.getCoordinate()))
            response.body().close()
            return false
        }
    }

    private static void writeFile(Response response, String directory, int type, String filename,
                                  String url, Dependency dependency, Dependency from)
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
            logger.info(INFO_DOWNLOAD_SUCCESS(dependency.getCoordinate(), url, from == null ? "ROOT" : from.getCoordinate(), file.path))
        } catch (Exception e)
        {
            e.printStackTrace()
        } finally
        {
            Closes.doClose(is, fos)
        }
    }

    static Dependency getDependency(String g, String a, String v)
    {
        return getDependency(g, a, v, null, null)
    }

    static Dependency getDependency(String g, String a, String v, Dependency from)
    {
        return getDependency(g, a, v, null, from)
    }

    static def maven = new MavenOfAlibaba()

    static Dependency getDependency(String groupId, String artifactId, String version, Scope scope, Dependency from)
    {
        return maven.getDependency(groupId, artifactId, version, scope, from)
    }
}
