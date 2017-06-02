package live.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.required;

/**
 * 
 * 商户管理
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	private static Logger log = LogManager.getLogger(IndexController.class);

	@RequestMapping(value = "/capture/site", method = RequestMethod.GET)
	@ResponseBody
	public String index(@RequestParam("address") String address){
		if ( address == null || "".equals(address.trim()) ){
			return "params error";
		}
		String picName = "";
		if ( address.indexOf("http://") != -1 ){
			picName = address.substring( address.indexOf("http://") + "http://".length() );
		}else if ( address.indexOf("https://") != -1 ){
			picName = address.substring( address.indexOf("http://") + "https://".length() );
		}else{
			picName = address;
		}
		picName += ".png";
		
		String output = "";
		String cmd = "phantomjs ";
		String osName = System.getProperty("os.name");  
		if( osName.startsWith("win") || osName.startsWith("Win") ){
			cmd += "H:/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/test/test.js ";
			output = "H:/phantomjs-2.1.1-windows/phantomjs-2.1.1-windows/test/";
		}else{
			cmd += "/phantomjs-2.1.1-linux-x86_64/js/sc.js ";
			output = "/img/";
		}
		
		output += picName;
		cmd += address + " "+ output;
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "OK";
	}

	@RequestMapping(value = "/about")
	public ModelAndView about(){
		Map map = new HashMap<String, Object>();
		map.put("purpose", "网站取证工具");
		return new ModelAndView("about", map);
	}
}
