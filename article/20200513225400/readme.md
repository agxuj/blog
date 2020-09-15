<h1 style="font-size: 2.5em;"> Spring Boot ������</h1>
 

参�??:[处理器拦截器（HandlerInterceptor）详解](https://www.jianshu.com/p/1e8d088c2be9)

## Spring boot 中的 Interceptor

### 实现 HandlerInterceptor
`````
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 进入Controller之前执行，预处理回调方法
     *
     * @return true表示继续流程（如调用下一个拦截器或处理器�?
     * false表示流程中断（如登录�?查失败），不会继续调用其他的拦截器或处理器，此时我们�?要�?�过response来产生响应；
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
    throws Exception {
        if (isLogin) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），
     * 此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理�?
     * modelAndView也可能为null�?
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 
    throws Exception {

    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调�?
     * 如�?�能监控中我们可以在此记录结束时间并输出消�?�时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
    throws Exception {

    }
}

`````

### 配置拦截�?
`````
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //�?要拦截的路径
        String[] addPathPatterns = {
                "/admin/**"
        };


        //不拦截的路径
        String[] excludePathPatterns = {
                "/admin/login",
                "/admin/register",
        };

        //注册登陆拦截�?
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns(excludePathPatterns);


        //注册权限拦截�?
        /*registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns()
                .excludePathPatterns();*/
    }
}
`````

## Java Web 中的 filter

### 实现 Filter
`````
@WebFilter(filterName = "AdminFilter", urlPatterns = "/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        if (request.getSession().getAttribute(V.adminId) == null) {
            response.sendRedirect("/login");
            return;
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
`````

### �? Application.java 中配�?
`````
@ServletComponentScan(basePackages={"filter.class"})
`````
