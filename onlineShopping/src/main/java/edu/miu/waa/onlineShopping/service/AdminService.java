package edu.miu.waa.onlineShopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.miu.waa.onlineShopping.domain.Admin;
import edu.miu.waa.onlineShopping.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Value("${mail.confirmation.subject}")
	private String confirmationSubject;

	public Admin saveUser(Admin admin) {
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		return adminRepository.save(admin);
	}
	
	public Admin findUserByUsername(String username) {
		return adminRepository.findByUsername(username);
	}

	public String getConfirmationSubject() {
		return confirmationSubject;
	}

	public void setConfirmationSubject(String confirmationSubject) {
		this.confirmationSubject = confirmationSubject;
	}
	
}
