package com.jdc.pos.security;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.pos.model.entity.Employee;
import com.jdc.pos.model.entity.EmployeeRole;
import com.jdc.pos.model.entity.EmployeeRole.Role;
import com.jdc.pos.model.repo.EmployeeRepo;

@Service
public class PosUserDetailsService implements UserDetailsService{
	
	@Value("${admin.name}")
	private String adminName;
	@Value("${admin.mail}")
	private String adminMail;
	@Value("${admin.password}")
	private String adminPass;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmployeeRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repo.findOneByEmail(username)
				.map(e -> User.builder()
						.username(username)
						.password(e.getPassword())
						.authorities(e.getRoles().stream().map(r -> r.getRole().name()).toArray(a -> new String[a]))
						.accountExpired(null != e.getRetire() && e.getRetire().isAfter(LocalDate.now()))
						.disabled(e.getAssign().isBefore(LocalDate.now()))
						.accountLocked(e.isPending())
						.build())
				.orElseThrow(() -> new UsernameNotFoundException(username));
	}
	
	@EventListener(classes = ContextRefreshedEvent.class)
	@Transactional
	void createAdminUser() {
		if(repo.count() == 0) {
			var admin = new Employee();
			admin.setName(adminName);
			admin.setEmail(adminMail);
			admin.setPassword(passwordEncoder.encode(adminPass));
			admin.setAssign(LocalDate.now());
			
			var role = new EmployeeRole();
			role.setEmployee(admin);
			role.setRole(Role.Manager);
			
			admin.setRoles(List.of(role));
			
			repo.save(admin);
		}
	}

}
