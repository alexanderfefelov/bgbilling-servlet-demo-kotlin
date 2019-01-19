package com.github.alexanderfefelov.bgbilling.servlet.demo

import bitel.billing.common.VersionInfo
import org.apache.log4j.Logger
import ru.bitel.common.logging.NestedContext
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DemoServletKotlin : HttpServlet() {

    override fun init() = wrap {
        logger.trace("init")
    }

    override fun destroy() = wrap {
        logger.trace("destroy")
    }

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) = wrap {
        logger.trace("doGet")
        val kernelVer = VersionInfo.getVersionInfo("server")
        response.writer.println("Hello, World!")
        response.writer.println(kernelVer.getModuleName() + " " + kernelVer.getVersionString())
    }

    override fun doPost(request: HttpServletRequest, response: HttpServletResponse) = wrap {
        logger.trace("doPost")
        super.doPost(request, response)
    }

    override fun doPut(request: HttpServletRequest, response: HttpServletResponse) = wrap {
        logger.trace("doPut")
        super.doPut(request, response)
    }

    override fun doDelete(request: HttpServletRequest, response: HttpServletResponse) = wrap {
        logger.trace("doDelete")
        super.doDelete(request, response)
    }

    override fun doHead(request: HttpServletRequest, response: HttpServletResponse) = wrap {
        logger.trace("doHead")
        super.doHead(request, response)
    }

    override fun doOptions(request: HttpServletRequest, response: HttpServletResponse) = wrap {
        logger.trace("doOptions")
        super.doOptions(request, response)
    }

    override fun doTrace(request: HttpServletRequest, response: HttpServletResponse) = wrap {
        logger.trace("doTrace")
        super.doTrace(request, response)
    }

    private fun <R> wrap(block: () -> R) {
        try {
            NestedContext.push(LOG_CONTEXT)
            block()
        } finally {
            NestedContext.pop()
        }
    }

    private val logger: Logger = Logger.getLogger(this::class.java)

    private val LOG_CONTEXT = "servlet"

}
