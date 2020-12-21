package com.dahan.eimoto.reflect;

/*
 * Creates on 2020/3/21.
 */

import com.dahan.eimoto.Objects;
import com.dahan.eimoto.collect.Lists;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * 静态{@link Field}工具类。
 *
 * @author tiansheng
 */
public class Fields {

    /**
     * 在{@param target}中获取{@link Field}。
     */
    public static Field[] getFields(Class<?> target) {
        return getFields(target, false);
    }

    /**
     * 在{@param target}中获取{@link Field}。
     */
    public static Field[] getFields(Class<?> target, boolean accessible) {
        Field[] fields = target.getFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(accessible);
        }
        return fields;
    }

    /**
     * 通过{@param annotations}获取{@link Field}。
     */
    public static Field[] getFields(Class<?> target,
                                    Class<? extends Annotation>[] annotations) {
        return getFields(target, annotations, false);
    }

    /**
     * 通过{@param annotations}获取{@link Field}。
     */
    public static Field[] getFields(Class<?> target,
                                    Class<? extends Annotation>[] annotations,
                                    boolean accessible) {
        return getFieldsByAnnotation(getFields(target, accessible), annotations);
    }

    /**
     * 在{@param target}中获取声明的{@link Field}。
     */
    public static Field[] getDeclaredFields(Class<?> target) {
        return getDeclaredFields(target, false);
    }

    /**
     * 在{@param target}中获取声明的{@link Field}。
     */
    public static Field[] getDeclaredFields(Class<?> target, boolean accessible) {
        Field[] fields = target.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(accessible);
        }
        return fields;
    }

    /**
     * 获取类的所有字段，包括父类的
     */
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    public static Field[] getDeclaredFieldsIncludeSuper(Class<?> classic, boolean accessible) {
        List<Class<?>> classes = ClassUtils.getSuperClasses(classic);
        List<Field> fields = Lists.newArrayList(getDeclaredFields(classic, accessible));
        for (Class<?> single : classes) {
            Field[] f = getDeclaredFields(single, true);
            if (f == null) continue;
            fields.addAll(Lists.of(f));
        }
        Field[] rf = new Field[fields.size()];
        return fields.toArray(rf);
    }

    /**
     * 通过{@param annotations}获取声明的{@link Field}。
     */
    public static Field[] getDeclaredFields(Class<?> target,
                                            Class<? extends Annotation>[] annotations) {
        return getFieldsByAnnotation(getDeclaredFields(target), annotations);
    }

    /**
     * 通过{@param annotations}获取声明的{@link Field}。
     */
    public static Field[] getDeclaredFields(Class<?> target,
                                            Class<? extends Annotation>[] annotations,
                                            boolean accessible) {
        return getFieldsByAnnotation(getDeclaredFields(target, accessible), annotations);
    }

    public static Field getDeclaredField(String name, Class<?> clazz) {
        return getDeclaredField(name, clazz, true, false, false);
    }

    public static Field getDeclaredField(String name,
                                         Class<?> clazz,
                                         boolean accessible,
                                         boolean nonStatic,
                                         boolean nonFinal) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(accessible);
            if (nonFinal) {
                Field modified = getDeclaredField("modifiers", Field.class);
                modified.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            }
            return field;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    /**
     * 通过{@param annotations}获取字段。
     * <p>
     * {@param accessible}是{@link Field＃setAccessible}
     */
    public static Field[] getFieldsByAnnotation(Field[] fields,
                                                Class<? extends Annotation>[] annotations) {
        List<Field> fields0 = Lists.newLinkedList();
        for (Field field : fields) {
            for (Class<? extends Annotation> annotation : annotations) {
                if (field.isAnnotationPresent(annotation)) {
                    fields0.add(field);
                    break;
                }
            }
        }
        Field[] fieldArray = new Field[fields0.size()];
        fields0.toArray(fieldArray);
        return fieldArray;
    }


    public static void set(Object instance, Object value, String field) {
        Class<?> objectClass = instance.getClass();
        set(objectClass, value, getDeclaredField(field, objectClass));
    }

    /**
     * 设置{@code Filed}的值。
     */

    public static void set(Object instance, Object value, Field field) {
        Objects.trycache(o -> {
            field.set(instance, value);
            return null;
        });
    }

    /**
     * 判断对象中成员的值是否等于
     *
     * @param object     实例对象
     * @param fieldName  成员名
     * @param fieldValue 比较的值
     * @return 如果fieldValue等于返回true
     */
    public static boolean eq(Object object, String fieldName, Object fieldValue) {
        if (fieldValue == null)
            throw new NullPointerException("成员之间比较的值不能为空");
        Field field = getDeclaredField(fieldName, object.getClass());
        try {
            Object value = field.get(object);
            return fieldValue.equals(value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Object getValue(Object object, String fieldName) {
        Field field = getDeclaredField(fieldName, object.getClass());
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}