package com.github.alexanderfefelov.bgbilling.servlet.demo

import bitel.billing.common.VersionInfo
import org.apache.log4j.Logger
import ru.bitel.common.logging.NestedContext
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DemoServletKotlin : HttpServlet() {

    override fun init() {
        try {
            NestedContext.push(LOG_CONTEXT)
            logger.trace("init")
        } finally {
            NestedContext.pop()
        }
    }

    override fun destroy() {
        try {
            NestedContext.push(LOG_CONTEXT)
            logger.trace("destroy")
        } finally {
            NestedContext.pop()
        }
    }

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            NestedContext.push(LOG_CONTEXT)
            logger.trace("doGet")
            val kernelVer = VersionInfo.getVersionInfo("server")
            response.writer.println("Hello, World!")
            response.writer.println(kernelVer.getModuleName() + " " + kernelVer.getVersionString())
        } finally {
            NestedContext.pop()
        }
    }

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            NestedContext.push(LOG_CONTEXT)
            logger.trace("doPost")
            super.doPost(request, response)
        } finally {
            NestedContext.pop()
        }
    }

    override fun doPut(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            NestedContext.push(LOG_CONTEXT)
            logger.trace("doPut")
            super.doPut(request, response)
        } finally {
            NestedContext.pop()
        }
    }

    override fun doDelete(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            NestedContext.push(LOG_CONTEXT)
            logger.trace("doDelete")
            super.doDelete(request, response)
        } finally {
            NestedContext.pop()
        }
    }

    override fun doHead(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            NestedContext.push(LOG_CONTEXT)
            logger.trace("doHead")
            super.doHead(request, response)
        } finally {
            NestedContext.pop()
        }
    }

    override fun doOptions(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            NestedContext.push(LOG_CONTEXT)
            logger.trace("doOptions")
            super.doOptions(request, response)
        } finally {
            NestedContext.pop()
        }
    }

    override fun doTrace(request: HttpServletRequest, response: HttpServletResponse) {
        try {
            NestedContext.push(LOG_CONTEXT)
            logger.trace("doTrace")
            super.doTrace(request, response)
        } finally {
            NestedContext.pop()
        }
    }

    private val logger: Logger = Logger.getLogger(this::class.java)

    private val LOG_CONTEXT = "servlet"

}
