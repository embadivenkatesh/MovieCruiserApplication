package com.stackroute.moviecruiserbackend.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;



public class JwtFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest)req;
		HttpServletResponse httpRes = (HttpServletResponse)res;
		String authHeader= httpReq.getHeader("authorization");
		if("OPTIONS".equals(httpReq.getMethod())){
			httpRes.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		}else{
			if(authHeader ==null || !authHeader.startsWith("Bearer ")){
				throw new ServletException("Invalid header");
				
			}
			
			String token = authHeader.substring(7);
			Claims claim = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
			httpReq.setAttribute("claims", claim);
			chain.doFilter(req, res);
			
		}
		
	}

}
