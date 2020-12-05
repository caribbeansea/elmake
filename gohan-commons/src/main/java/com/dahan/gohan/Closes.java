package com.dahan.gohan;

/*
 * Creates on 2020/5/14.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author kevin
 */
public class Closes {

  private static final Logger mLog = LoggerFactory.getLogger(Closes.class);

  public static void doClose(Closeable... closeables) {
    try {
      if (closeables == null) return;
      for (Closeable closeable : closeables) {
        if (closeable == null) continue;
        closeable.close();
      }
    }catch (IOException e){
      mLog.error("close failure.", e);
    }
  }

}