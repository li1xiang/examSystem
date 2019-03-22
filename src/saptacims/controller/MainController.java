package saptacims.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页
 * @author Administrator
 *
 */
@Controller
public class MainController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping("/main")
	public String getMain(){
		logger.info("跳转到首页:main......");
		return "main";
	}
	
	@RequestMapping("/subpageDefault")
	public String subpageDefault(){
		return "subpage-default";
	}
	
}
