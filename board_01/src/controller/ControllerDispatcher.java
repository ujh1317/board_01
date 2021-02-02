package controller;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

import command.CommandAction;

public class ControllerDispatcher extends HttpServlet{
	private Map<String,Object> map = new HashMap<String,Object>();
	
	public void init(ServletConfig config) throws ServletException{
		String path = config.getServletContext().getRealPath("/");
		String pros = path+config.getInitParameter("profile");
		Properties pp = new Properties();
		FileInputStream fn = null;
		try{
			fn = new FileInputStream(pros);
			pp.load(fn);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				fn.close();
			}catch(Exception e){}
		}//finally
		
		Iterator keys = pp.keySet().iterator();
		
		while(keys.hasNext()){
			String kkey = (String)keys.next();
			String className = pp.getProperty(kkey);
			try{
				Class commandClass = Class.forName(className);
				Object commandObject = commandClass.newInstance();
				
				map.put(kkey, commandObject);
			}catch(Exception e){
				e.printStackTrace();
			}//catch
		}//while
	}//init()
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		reqPro(request,response);
	}//doGet()
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		reqPro(request,response);
	}//doPost()
	
	private void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
		String view = "";
		CommandAction commandAction = null;
		try{
			String uri = request.getRequestURI();
			if(uri.indexOf(request.getContextPath())==0){
				uri = uri.substring(request.getContextPath().length());
			}//if
		}catch(Throwable e){
			throw new ServletException(e);
		}//catch
	}//reqPro()
}//class
