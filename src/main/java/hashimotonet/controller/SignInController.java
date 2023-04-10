package hashimotonet.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hashimotonet.action.ListImagesAction;

/**
 * @author hashi
 *
 */
@Controller
@RequestMapping(path="SignIn", method = RequestMethod.POST)
public class SignInController {
  
  @Autowired
  HttpServletRequest request;
	
  @RequestMapping(method=RequestMethod.POST)
  public String index(HttpServletResponse response, @RequestParam("id") String id, Model model) throws IOException {
	  model.addAttribute("id", id);
	  
	  boolean androidApp = new ListImagesAction().isAndroidApp(request);
	  
	  if (androidApp) {
		  response.setCharacterEncoding("UTF-8");
		  response.getWriter().print("success");
		  return null;
	  } else {
	      return "photo";
	  }
    //return "display";
    //return "redirect:/ListImages";
  }
}
