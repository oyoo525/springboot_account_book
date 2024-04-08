package com.mysite.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.account.service.FileService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FileController {

	@Autowired
	private FileService fileService;
	
	
	@GetMapping("excelUpload")
	public String excelUploadPage() {
		return "excel";
	}
	
	@PostMapping("fileUpload")
	public String addCsv(@RequestParam("fileUpload") MultipartFile file, HttpSession session) throws Exception {
		String id = (String)session.getAttribute("loginId");
		
		fileService.createCsv(file, id);
		
		return "";
	}
	
}
