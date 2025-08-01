package com.bbd.HospitalManagement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
		String redirectUrl = "/";

		if (session.getAttribute("doctorId") != null) {
			redirectUrl = "/doctor/login";	//Doctor Logout
		} else if (session.getAttribute("patientId") != null) {
			redirectUrl = "/patient/login";	//Patient Logout
		} else if (session.getAttribute("adminLoggedIn") != null) {
	        redirectUrl = "/admin/login";  //Admin logout 
	    }
		session.invalidate();
		redirectAttributes.addFlashAttribute("logoutSuccess", "Logout Successful!!");
		return "redirect:" + redirectUrl;
	}
}
