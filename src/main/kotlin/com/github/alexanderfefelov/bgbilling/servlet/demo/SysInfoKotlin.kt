package com.github.alexanderfefelov.bgbilling.servlet.demo

import bitel.billing.common.VersionInfo
import org.apache.log4j.Logger
import ru.bitel.bgbilling.kernel.module.server.ModuleCache
import ru.bitel.common.logging.NestedContext
import java.net.InetAddress
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SysInfoKotlin : HttpServlet() {

    override fun init() = wrap {
        logger.trace("init")
    }

    override fun destroy() = wrap {
        logger.trace("destroy")
    }

    override fun doGet(request: HttpServletRequest, response: HttpServletResponse) = wrap {
        logger.trace("doGet")

        response.writer.println(collectSysInfo())
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

    private fun collectSysInfo(): String {
        return """${collectModules()}
            |            
            |${collectRuntime()}""".trimMargin()
    }

    private fun collectModules(): String {
        return """Modules
            |$HR
            |
            |${inspectKernel()}
            |${inspectModules()}""".trimMargin()
    }

    private fun inspectKernel(): String {
        val kernelVer = VersionInfo.getVersionInfo("server")
        return "0 ${kernelVer.moduleName} ${kernelVer.versionString}"
    }

    private fun inspectModules(): String {
        return ModuleCache.getInstance().modulesList.joinToString (separator = NL) { module ->
            val ver = VersionInfo.getVersionInfo(module.name)
            "${module.id} ${ver.moduleName} ${ver.versionString}"
        }
    }

    private fun collectRuntime(): String {
        return """Runtime
            |$HR
            |                        
            |Hostname/IP address: ${InetAddress.getLocalHost()}
            |Available processors: ${Runtime.getRuntime().availableProcessors()}
            |Memory free / max / total, MB: ${Runtime.getRuntime().freeMemory() / MB} / ${Runtime.getRuntime().maxMemory() / MB} / ${Runtime.getRuntime().totalMemory() / MB}""".trimMargin()
    }

    private fun <R> wrap(block: () -> R) {
        try {
            NestedContext.push(LOG_CONTEXT)
            block()
        } finally {
            NestedContext.pop()
        }
    }

    private val logger = Logger.getLogger(this::class.java)

    private val LOG_CONTEXT = "servlet"

    private val HR = "--------------------------------------------------"
    private val NL = "\n"
    private val MB = 1024 * 1024

}
