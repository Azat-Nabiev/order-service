package com.example.apigateway.config

import io.opentelemetry.api.trace.Tracer
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TracingFilterConfig (private val tracer : Tracer) {
    @Bean
    fun tracingGlobalFilter(): GlobalFilter {
        return GlobalFilter { exchange, chain ->

            val span = tracer.spanBuilder("api-gateway-span")
                .startSpan()

            val mutatedRequest = exchange.request.mutate()
                .header("traceId", span.spanContext.traceId)
                .build()

            val mutatedExchange = exchange.mutate()
                .request(mutatedRequest)
                .build()

            chain.filter(mutatedExchange).doFinally {
                span.end()
            }
        }
    }
}