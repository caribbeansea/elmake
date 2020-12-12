package com.dahan.gohan;
/*
 * Creates on 2020/10/27.
 */

/**
 * @author kevin
 */
public interface Execute<T> {
  T accept(Object o) throws Throwable;
}
