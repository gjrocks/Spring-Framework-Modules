package com.gj;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.newrelic.api.agent.ConcurrentHashMapHeaders;
import com.newrelic.api.agent.HeaderType;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;

@Component
@Order(100)
public class NRFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(NRFilter.class);
    @Override
    @Trace(dispatcher = true)
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        final HttpServletRequest request = (HttpServletRequest) req;
       // traceparent:tr,
      //  tracestate: "rojo=00f067aa0ba902b7,congo=t61rcWkgMzE"
       /* Transaction tr=  NewRelic.getAgent().getTransaction();
        ConcurrentHashMapHeaders nrHeaders=ConcurrentHashMapHeaders.build(HeaderType.HTTP);
        nrHeaders.addHeader("traceparent", "00-ganeshjadhavkijaiho-B12356-01");
        nrHeaders.addHeader("tracestate", "rojo=00f067aa0ba902b7,congo=t61rcWkgMzE");
        tr.insertDistributedTraceHeaders(nrHeaders);
        */
        //request
        String traceState=request.getHeader("tracestate");
    	//System.out.println("TraceState :"+ traceState);
    	addToTrace(traceState);
    //	Transaction tr=  NewRelic.getAgent().getTransaction();
    //	OutboundHeaders ot=null;
     //   ConcurrentHashMapHeaders nrHeaders=ConcurrentHashMapHeaders.build(HeaderType.HTTP);
       // nrHeaders.addHeader("traceparent", "00-ganeshjadhavkijaiho-B12356-01");
     //   nrHeaders.addHeader("Ganesh", "jadhav");
      //  NewRelic.getAgent().getTracedMethod().addCustomAttribute("userId", "Ganesh");
     //   tr.insertDistributedTraceHeaders(nrHeaders);
    	
    	  //get the logged in user name or id from the security filter or security context 
    	    MDC.put("session-user", "cqktss0");
    	    
            chain.doFilter(req, res);
            
            //response 
            Transaction tx = NewRelic.getAgent().getTransaction();
            tx.addOutboundResponseHeaders();
            tx.markResponseSent();
           // MDC.remove("user");
    }
 private void addToTrace(String traceState) {
	 Transaction tr=  NewRelic.getAgent().getTransaction();
	 if(StringUtils.hasText(traceState)) {
 		String headers[]=traceState.split(",");
 		if(headers!=null && headers.length>0) {
 		for (String header : headers) {
				String pair[]=header.split("=");
				//System.out.println(pair[0]);
				//System.out.println(pair[1]);
				NewRelic.getAgent().getTracedMethod().addCustomAttribute(pair[0], pair[1]);
				 ConcurrentHashMapHeaders nrHeaders=ConcurrentHashMapHeaders.build(HeaderType.HTTP);
			       // nrHeaders.addHeader("traceparent", "00-ganeshjadhavkijaiho-B12356-01");
			        nrHeaders.addHeader(pair[0],  pair[1]);
			        RequestHolder.addHeaders(pair[0], pair[1]);
			        tr.insertDistributedTraceHeaders(nrHeaders);
			        if(pair[0]!=null && pair[0].trim().equalsIgnoreCase("source")) {
			        	  NewRelic.setTransactionName("Custom", pair[1]);
			        	 
			        }
			}
 		}
 	}
 }
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
}