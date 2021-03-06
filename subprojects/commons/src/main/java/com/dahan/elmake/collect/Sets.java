package com.dahan.elmake.collect;

/*
 * Copyright (C) 2020 tiansheng All rights reserved.
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
 */

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
 * Creates on 2020/3/11.
 */


import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * 静态的Set工具类
 *
 * @author tiansheng
 */
public final class Sets {

//  /**
//   * 创建元素列表不可变的Set, 元素列表已经是固定了的, 是不可变的。
//   * 如果我们已经初始化了2个元素，那么我们再添加一个元素就会抛出一个叫做
//   * {@link UnsupportedOperationException}的异常。
//   *
//   * @param elements Set元素
//   * @param <E>      元素类型
//   * @return 返回一个列表不可变的Set
//   */
//  public static <E> Set<E> newImmutableSet(E... elements) {
//    return Set.of(elements);
//  }
//
//  /**
//   * 复制一个Set中的元素创建出一个列表不可变的Set
//   */
//  public static <E> Set<E> newImmutableSet(Set<E> collections) {
//    return Set.copyOf(collections);
//  }

  /**
   * 创建一个新的且空的{@code HashSet}实例
   */
  public static <E> HashSet<E> newHashSet() {
    return new HashSet<>();
  }

  /**
   * 创建一个可变的 {@code HashSet}实例
   * 拷贝{@link Collection}中的数据到新的{@code HashSet}中。
   */
  public static <E> HashSet<E> newHashSet(Collection<? extends E> collection) {
    return new HashSet<>(collection);
  }

  /**
   * 创建一个新的{@code LinkedHashSet}实例
   */
  public static <E> LinkedHashSet<E> newLinkedHashSet() {
    return new LinkedHashSet<>();
  }

  /**
   * 创建一个新的{@code LinkedHashSet}实例
   * 拷贝{@link Collection}中的数据到新的{@code LinkedHashSet}中。
   */
  public static <E> LinkedHashSet<E> newLinkedHashSet(Collection<? extends E> collection) {
    return new LinkedHashSet<>(collection);
  }

}
