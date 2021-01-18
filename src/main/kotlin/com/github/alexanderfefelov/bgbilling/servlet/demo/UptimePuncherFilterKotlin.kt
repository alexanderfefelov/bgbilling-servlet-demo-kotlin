package com.github.alexanderfefelov.bgbilling.servlet.demo

import org.apache.log4j.Logger
import ru.bitel.common.logging.NestedContext
import javax.servlet.*
import javax.servlet.http.HttpServletResponse

class UptimePuncherFilterKotlin : Filter {

    override fun init(filterConfig: FilterConfig) = wrap {
        logger.trace("init")
    }

    override fun destroy() = wrap {
        logger.trace("destroy")
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) = wrap {
        logger.trace("doFilter")

        chain.doFilter(request, response)
        val httpResponse = response as HttpServletResponse
        httpResponse.addHeader("X-Clacks-Overhead", "GNU Terry Pratchett")
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

}
