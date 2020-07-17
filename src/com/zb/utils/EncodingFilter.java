package com.zb.utils;

import javax.servlet.*;
import java.io.IOException;

//我们自己的类想要具有过滤器的功能需要实现Filter接口
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化过滤器");
    }
    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器准备就绪");
        //过滤器设置编码
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        //放行：执行后续的操作
        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
