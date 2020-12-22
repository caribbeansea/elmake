package com.dahan.elmake.dsl.groovy

import com.dahan.elmake.dsl.AbsMake

/* ************************************************************************
 *
 * Copyright (C) 2020 dahan All rights reserved.
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
 * Creates on 2020/12/21.
 */

/**
 * Groovy DSL
 *
 * @author tiansheng
 */
class MakeOfGroovy extends AbsMake {

    void include(Map<String, String> settings) {
        super.include(settings.coords, settings.scope, settings.classifier,
                settings.ext)
    }

}