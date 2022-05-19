package com.incubator4.util

import kotlinx.coroutines.sync.Mutex
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.User

private val userThrottleLockMap = mutableMapOf<Long, Mutex>()

private val groupThrottleLockMap = mutableMapOf<Long, Mutex>()

private fun getUserThrottleLock(id: Long): Mutex {
    return userThrottleLockMap.getOrPut(id) { Mutex() }
}

private fun getGroupThrottleLock(id: Long): Mutex {
    return groupThrottleLockMap.getOrPut(id) { Mutex() }
}

/**
 * 上锁以节流
 *
 * @param subject 联系对象
 * @return 上锁结果
 * @see CommandSender.subject
 */
fun lock(subject: Contact?): Boolean {
    val mutex = when (subject) {
        is User -> getUserThrottleLock(subject.id)
        is Group -> getGroupThrottleLock(subject.id)
        else -> null
    }
    return mutex?.tryLock() ?: true
}

/**
 * 解锁并从map中移除锁
 *
 * @param subject 联系对象
 * @see CommandSender.subject
 */
fun unlock(subject: Contact?) {
    val mutex = when (subject) {
        is User -> userThrottleLockMap.remove(subject.id)
        is Group -> groupThrottleLockMap.remove(subject.id)
        else -> null
    }
    mutex?.unlock()
}
