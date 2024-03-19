package com.capstone.onlinemoviebooking.service;

import com.capstone.onlinemoviebooking.dto.UserDTO;
import com.capstone.onlinemoviebooking.model.Role;
import com.capstone.onlinemoviebooking.model.User;
import com.capstone.onlinemoviebooking.repository.UserRepository;
import com.capstone.onlinemoviebooking.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleService roleService;

    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService,  @Lazy BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(userName);
        log.debug(userName);
        if (user == null) {
            log.warn("Invalid username or password {}", userName);

            throw new UsernameNotFoundException("Invalid username or password.");
        }
       /* return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));*/

        return new UserPrincipal(user, roleService.getRolesByUser(user.getId()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }


    /**
     * Using model mapper
     * helps to avoid extra
     * coding
     * @param userDTO
     */
    @Transactional
    public void creat(UserDTO userDTO)
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDTO, User.class);

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleService.findRoleByRoleName("ROLE_GUEST")));

        userRepository.save(user);
    }

    /**
     * In this example
     * login and email
     * has the same values
     * @param email
     * @return
     */
    public User findUserByEmail(String email)
    {
        return userRepository.findUserByEmail(email);
    }

    public User findUserByName(String name)
    {
        return userRepository.findUserByUserName(name);
    }
}
