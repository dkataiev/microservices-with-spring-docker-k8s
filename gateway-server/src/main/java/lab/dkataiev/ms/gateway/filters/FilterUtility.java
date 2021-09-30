package lab.dkataiev.ms.gateway.filters;

import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

public class FilterUtility {

    public static final String CORRELATION_ID = "k8s-bank-correlation-id";

    public static String getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(CORRELATION_ID) != null) {
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID);
            return requestHeaderList.stream().findFirst().get();
        } else {
            return null;
        }
    }

    public static ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public static ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

}
