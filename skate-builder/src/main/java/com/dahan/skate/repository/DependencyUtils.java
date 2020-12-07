package com.dahan.skate.repository;

import com.dahan.skate.collect.Maps;
import com.dahan.skate.repository.dependency.Dependency;

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

    public static Map<String, Dependency> getDependencyMap()
    {
        return dependencyMap;
    }

    public static Dependency getLoadedDependency(Dependency dependency)
    {
        return dependencyMap.get(dependency.getCoordinate());
    }

}
