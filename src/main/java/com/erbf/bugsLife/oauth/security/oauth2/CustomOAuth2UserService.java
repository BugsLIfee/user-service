package com.erbf.bugsLife.oauth.security.oauth2;

import com.erbf.bugsLife.oauth.exception.OAuth2AuthenticationProcessingException;
import com.erbf.bugsLife.oauth.model.AuthProvider;
import com.erbf.bugsLife.oauth.model.User;
import com.erbf.bugsLife.oauth.repository.UserRepository;
import com.erbf.bugsLife.oauth.security.UserPrincipal;
import com.erbf.bugsLife.oauth.security.oauth2.user.OAuth2UserInfo;
import com.erbf.bugsLife.oauth.security.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = User.builder()
        		.provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
        		.providerId(oAuth2UserInfo.getId())
        		.name(oAuth2UserInfo.getName())
        		.role(oAuth2UserInfo.getRole().USER)
        		.email(oAuth2UserInfo.getEmail())
                .enrollDate(LocalDateTime.now())
                .emailVerified(true)
        		.imageUrl(oAuth2UserInfo.getImageUrl())
                .attendBefore(false)
                .attendCnt(0)
                .isAttend(false)
        		.build();
        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        System.out.println("������Ʈ�� existingUser::"+existingUser);
    	User user = existingUser.update(oAuth2UserInfo.getName(), oAuth2UserInfo.getImageUrl());
    	//existingUser.updatename(oAuth2UserInfo.getName())
        //existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(user);
    }

}
