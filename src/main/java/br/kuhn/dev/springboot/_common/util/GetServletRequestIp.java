package br.kuhn.dev.springboot._common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Jardel Kuhn (jkuhn2@universo.univates.br)
 */
@Service
public class GetServletRequestIp {

    public String parse(HttpServletRequest request) throws UnknownHostException {
        String ipAddress = request.getHeader("x-forwarded-for");

        if (isInvalidAddress(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (isInvalidAddress(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (isInvalidAddress(ipAddress)) {
            ipAddress = request.getRemoteAddr();

            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                InetAddress inet = null;

                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    throw e;
                }
            }
        }

        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    private boolean isInvalidAddress(String ipAddress) {
        return ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress);
    }
}
