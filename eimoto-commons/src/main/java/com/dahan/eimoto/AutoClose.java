package com.dahan.eimoto;

/*
 * Creates on 2020/5/14.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author tiansheng
 */
public class AutoClose
{

    private static final Logger LOG = LoggerFactory.getLogger(AutoClose.class);

    public static void close(Closeable... closeables)
    {
        try
        {
            if (closeables == null) return;
            for (Closeable closeable : closeables)
            {
                if (closeable == null) continue;
                closeable.close();
            }
        } catch (IOException e)
        {
            LOG.error("close failure.", e);
        }
    }

}