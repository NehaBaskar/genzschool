package com.sample.genzschool.AuthenticationSecurity;

import com.sample.genzschool.Model.Person;
import com.sample.genzschool.Model.Roles;
import com.sample.genzschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenZSchoolUserPwdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName(); /* Name is the email provided by user */
        String pwd = authentication.getCredentials().toString();
        Person person = personRepository.findByEmail(email);

        if(null != person && person.getPersonId() > 0 && passwordEncoder.matches(pwd,person.getPwd())){
            return new UsernamePasswordAuthenticationToken(email, null /*credentials should be null*/, getGrantedAuthorities(person.getRoles()) );
        }else{
            throw  new BadCredentialsException("Invalid Credentials !!!");
        }
    }


    public List<GrantedAuthority> getGrantedAuthorities(Roles roles){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+ roles.getRoleName()));
        return grantedAuthorities;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
