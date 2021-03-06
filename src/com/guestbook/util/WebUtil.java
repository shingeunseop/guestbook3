package com.guestbook.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	
	public static void forward(HttpServletRequest request, HttpServletResponse response,String path) 
			throws ServletException, IOException {
		
		
		RequestDispatcher rd=request.getRequestDispatcher(path);
	    rd.forward(request, response);//복잡한 로직의 경우 이렇게 만들어 놓으면 편함
	    
	    
	    //WebUtil.forward(request, response, "/WEB-INF/views/main/list.jsp");
	//이와 같은 형태로 쓰여짐
	}
	
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response,String url)
			throws IOException {
		
		response.sendRedirect(url);
	}

}
