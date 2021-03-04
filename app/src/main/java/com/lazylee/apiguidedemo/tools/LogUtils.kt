package com.lazylee.apiguidedemo.tools

import android.util.Log
import com.lazylee.apiguidedemo.BuildConfig
import java.util.*

/**
 * 日志打印工具
 */
class LogUtils {

    companion object {
        /**
         * 日志打印等级
         */
        const val ERROR = 10
        const val WARNING = 20
        const val INFO = 30
        const val DEBUG = 40
        const val VERBOSE = 50

        /**
         * 打印Verbose等级日志
         * @param tag   日志tag
         * @param msg   日志内容
         */
        @JvmStatic
        fun v(tag: String, msg: String) {
            printTag(VERBOSE, tag, msg, null)
        }

        /**
         * 打印Debug等级日志
         * @param tag   日志tag
         * @param msg   日志内容
         */
        @JvmStatic
        fun d(tag: String, msg: String) {
            printTag(DEBUG, tag, msg, null)
        }

        /**
         * 打印Info等级日志
         * @param tag   日志tag
         * @param msg   日志内容
         */
        @JvmStatic
        fun i(tag: String, msg: String) {
            printTag(INFO, tag, msg, null)
        }

        /**
         * 打印Warning等级日志
         * @param tag   日志tag
         * @param msg   日志内容
         */
        @JvmStatic
        fun w(tag: String, msg: String) {
            printTag(WARNING, tag, msg, null)
        }

        /**
         * 打印Error等级日志
         * @param tag   日志tag
         * @param msg   日志内容
         */
        @JvmStatic
        fun e(tag: String, msg: String) {
            printTag(ERROR, tag, msg, null)
        }

        @JvmStatic
        fun printTag(level: Int, tag: String, format: String, args: Array<Any>?) {
            var mLevel = 0
            if (level == VERBOSE || level == DEBUG) {
                //非Debug构建过滤低级别日志信息
                if (!BuildConfig.DEBUG) return
            }

            if (level == WARNING) mLevel = ERROR

            try {
                val message: String =
                        if (args == null || args.isEmpty()) format else String.format(format, args)

                if (BuildConfig.DEBUG) {
                    Log.println(toSysLevel(mLevel), tag, message)
                }

            } catch (e: MissingFormatArgumentException) {
                e.printStackTrace()
            }


        }

        private fun toSysLevel(level: Int): Int {
            return when (level) {
                VERBOSE -> Log.VERBOSE
                DEBUG -> Log.DEBUG
                INFO -> Log.INFO
                WARNING -> Log.WARN
                ERROR -> Log.ERROR
                else -> Log.INFO
            }
        }

    }
}