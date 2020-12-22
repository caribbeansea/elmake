package com.dahan.elmake;

/*
 * Creates on 2019/11/13.
 */

import com.dahan.elmake.exception.SerializationException;
import com.dahan.elmake.reflect.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author tiansheng
 */
public class Objects
{

    private static final Logger LOG = LoggerFactory.getLogger(Objects.class);

    /**
     * 序列化对象
     *
     * @param value 对象实例
     * @return 序列化后的字节码
     */
    public static byte[] serialize(Object value)
    {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try
        {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
            return baos.toByteArray();
        } catch (IOException e)
        {
            LOG.error("serializing error", e);
        } finally
        {
            AutoClose.close(baos, oos);
        }
        throw new SerializationException("对象序列化失败");
    }

    /**
     * 对象反序列化
     *
     * @param bytes 字节码
     * @return 反序列化后的对象
     */
    public static Object unserialize(byte[] bytes)
    {
        ObjectInputStream ois = null;
        ByteArrayInputStream bais = null;
        try
        {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e)
        {
            LOG.error("unserializing error", e);
        } finally
        {
            AutoClose.close(bais, ois);
        }
        throw new SerializationException("反序列化失败");
    }

    public static <T> T trycache(Execute<T> runnable)
    {
        return trycache(runnable, null, null);
    }

    public static <T> T trycache(Execute<T> runnable, Class<? extends RuntimeException> e)
    {
        return trycache(runnable, e, null);
    }

    /**
     * 捕获异常代码块
     *
     * @param runnable   执行方法
     * @param e          如果发生异常抛出哪个异常类，如果为空使用默认抛出的异常类并直接打印异常信息
     * @param message    如果发生异常，自定义异常类的构造器是使用message还是使用抛出的异常信息
     * @param closeables 如果存在需要关闭的流对象
     */
    public static <T> T trycache(Execute<T> runnable,
                                 Class<? extends RuntimeException> e,
                                 String message,
                                 Closeable... closeables)
    {
        T t = null;
        try
        {
            t = runnable.accept(null);
        } catch (Throwable e1)
        {
            if (e != null)
            {
                if (StringUtils.isEmpty(message))
                {
                    throw ClassUtils.<RuntimeException>newInstance(e, new Class[]{Throwable.class}, e1);
                } else
                {
                    throw ClassUtils.<RuntimeException>newInstance(e, message);
                }
            } else
            {
                e1.printStackTrace();
            }
        } finally
        {
            if (closeables != null && closeables.length > 0)
            {
                try
                {
                    for (Closeable closeable : closeables)
                    {
                        if (closeable != null)
                        {
                            closeable.close();
                        }
                    }
                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
        return t;
    }

}
