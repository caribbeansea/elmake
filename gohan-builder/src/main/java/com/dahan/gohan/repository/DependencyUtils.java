package com.dahan.gohan.repository;

import com.dahan.gohan.collect.Maps;
import com.dahan.gohan.repository.dependency.Dependency;

import java.util.Map;

/**
 * @author kevin
 */
public class DependencyUtils
{

    private static final Map<String, Dependency> dependencyMap = Maps.newConcurrentHashMap(64);

    public static boolean isJar(int type)
    {
        return Dependency.getJAR() == type;
    }

    public static boolean contains(Dependency dependency)
    {
        return dependencyMap.containsKey(dependency.getCoordinate());
    }

    public static void loaded(Dependency dependency)
    {
        dependencyMap.put(dependency.getCoordinate(), dependency);
    }

    public static Dependency getLoadedDependency(Dependency dependency)
    {
        return dependencyMap.get(dependency.getCoordinate());
    }

}
