package com.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.guestbook.dao.GuestbooksDao;
import com.guestbook.vo.GuestbooksVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbooksDao dao;

	@RequestMapping("/list")
	public String list(Model model) {

		List<GuestbooksVo> list = dao.getList();
		System.out.println(list.toString());
		model.addAttribute("list", list);

		return "/list";
	}

	@RequestMapping("/deleteform")
	public String deleteform() {
				

		return "/deleteform";
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)//보낼때 post의 형태로 보냈다면 옆과 같이 써줘야 받는다. 
	public String delete(@ModelAttribute GuestbooksVo vo ) {
		
		System.out.println("aaaa");
		dao.delete(vo.getNo(),vo.getPassword());
		
		
		
		return "redirect:/list";//redirect하는 변수 입	력해 줘야함
		
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute GuestbooksVo vo) {
		
		dao.insert(vo.getName(),vo.getPassword(),vo.getContent());
		
		return "redirect:/list";
	}

}
