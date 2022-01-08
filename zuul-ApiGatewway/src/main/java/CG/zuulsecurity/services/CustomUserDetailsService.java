package CG.zuulsecurity.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import CG.zuulsecurity.models.User;
import CG.zuulsecurity.repositories.RoleRepository;
import CG.zuulsecurity.repositories.UserRepository;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import CG.zuulsecurity.models.Role;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	public User findUserByUsername(String username) {
	    return userRepository.findByUsername(username);
	}

	//save user
	public void saveUser(User user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    System.out.println(user.getRoles());
		List<Role> roles = user.getRoles().stream()
				.filter(roleObj -> (roleObj.getRole().equals("ADMIN") || roleObj.getRole().equals("USER")))
				.collect(Collectors.toList());

		System.out.println(user);
		user.setRoles(roles);
//	    Iterator i=user.getRoles().iterator();
//		if(user.getRoles().size()==0)
//		{
//			Role userRole = roleRepository.findByRole("USER");
//			user.setRoles((Arrays.asList(userRole)));
//		}
//	    while (i.hasNext()) {
//            Role r=(Role) i.next();
//			if(r.getRole()==null)
//			{
//				Role userRole = roleRepository.findByRole("USER");
//				user.setRoles(((Arrays.asList(userRole))));
//			}
//			else
//			{
//				if(r.getRole().equals("ADMIN") || r.getRole().equals("USER"))
//				{
//					Role userRole = roleRepository.findByRole(r.getRole());
//					user.setRoles((Arrays.asList(userRole)));
//
//				}
//			}
//        }
	    userRepository.save(user);
	    System.out.println(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	    User user = userRepository.findByUsername(username);
	    if(user != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
	        return buildUserForAuthentication(user, authorities);
	    } else {
	        throw new UsernameNotFoundException("Username not found in database");
	    }
	}
	
	private List<GrantedAuthority> getUserAuthority(List<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
		String ROLE_PREFIX = "ROLE_";
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(ROLE_PREFIX+role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
}