//package com.projectmanager.config.security;
//
//import com.projectmanager.model.entity.UserEntity;
//import com.projectmanager.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class CustomUserDetailService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        UserEntity userEntity = userRepository.findByName(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found by name " + username));
//
//
//        return new CustomUserDetails(userEntity);
//    }
//
////    @Override
////    public UserDetails updatePassword(UserDetails user, String newPassword) {
////        String username = user.getUsername();
////        MutableUserDetails mutableUser = (MutableUserDetails)this.users.get(username.toLowerCase());
////        mutableUser.setPassword(newPassword);
////        return mutableUser;
////    }
//}