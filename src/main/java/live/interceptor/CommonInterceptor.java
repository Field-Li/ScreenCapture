package live.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import live.util.Const;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CommonInterceptor implements HandlerInterceptor{
	
	private static Logger log = LogManager.getLogger(CommonInterceptor.class);
	List<String> noCheckList = Arrays.asList(
			"/",
			"/capture/site",
			"/userSys/toLogin", 
			"/userSys/login", 
			"/userSys/register");
    
    @Override
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {
		/*if ( ! noCheckList.contains(request.getServletPath())
    			&& request.getSession().getAttribute(Const.userSession) == null){
    		
    		response.sendRedirect(request.getContextPath()+"/userSys/toLogin");
    		return false;
    	}*/
    	request.setAttribute("path", request.getContextPath());
        log.info("ServletPath:" + request.getServletPath());
        return true;  
    }  
    
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {  

    }  
  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  

    }  
  
}