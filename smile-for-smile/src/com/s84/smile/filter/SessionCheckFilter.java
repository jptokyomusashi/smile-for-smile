package com.s84.smile.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SessionCheckFilter implements Filter {

	private FilterConfig filterConfig;
	@Override
	public void destroy() {
		//
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		String requestUri = request.getRequestURI();
		String[] ignores = filterConfig.getInitParameter("ignore").split(",");
		
		for (int i = 0; i < ignores.length; i++) {
			//チェック対象外
			if (requestUri.startsWith(ignores[i])) {
				filterChain.doFilter(servletRequest, servletResponse);
				return;
			}		
		}
		//セッションタイムアウト時
		if (request.getSession(false) == null) {
			request.getRequestDispatcher(filterConfig.getInitParameter("forward")).forward(servletRequest, servletResponse);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

}
