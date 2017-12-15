package com.adamant.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.adamant.entity.UserEntity;
import com.adamant.service.IUserService;

@Controller
@RequestMapping("/userController")
public class UserController {
	
	@Autowired
    private IUserService userService;  
      
    @RequestMapping("/xtest")
    public ModelAndView toIndex(HttpServletRequest request,Model model){
    	String param1 = request.getParameter("param1");
    	String param2 = request.getParameter("param2");
    	System.out.println("xxxxxxxxxxxxxxxxxxxx param1:"+param1);
    	System.out.println("xxxxxxxxxxxxxxxxxxxx param2:"+param2);
    	UserEntity user = this.userService.getUserById("xxxxxxxx");
    	model.addAttribute("user", user);
    	
    	String xInitParam = (String) request.getServletContext().getAttribute("xInitParam");
    	System.out.println("************xInitParam:"+xInitParam+"************");
    	
    	return new ModelAndView("/xtest");
    }
    
    @RequestMapping("/ajaxTest")
    @ResponseBody
    public Map<String, Object> ajaxDemo(){
    	Map<String, Object>	result = new HashMap<String, Object>();
    	result.put("msg", "tttttttttttttttttttttttttttt");
    	
    	return result;
    }
	
    
    
    
    
}
