package com.dahan.eimoto;
/*
 * Creates on 2020/10/27.
 */

/**
 * @author tiansheng
 */
public interface Execute<T> {
  T accept(Object o) throws Throwable;
}
