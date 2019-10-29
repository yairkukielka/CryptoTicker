/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yairkukielka.ticker.executor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadExecutor : Executor {
    private val threadPoolExecutor: ThreadPoolExecutor

    init {
        val corePoolSize = CORE_POOL_SIZE
        val maxPoolSize = MAX_POOL_SIZE
        val keepAliveTime = KEEP_ALIVE_TIME
        val workQueue = LinkedBlockingQueue<Runnable>()
        threadPoolExecutor = ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime.toLong(), TimeUnit.SECONDS, workQueue)
    }

    override fun run(runnable: Runnable?) {
        requireNotNull(runnable) { "runnable to execute can't be null" }
        threadPoolExecutor.submit(runnable)
    }

    companion object {
        private const val CORE_POOL_SIZE = 8
        private const val MAX_POOL_SIZE = 16
        private const val KEEP_ALIVE_TIME = 120
    }
}
