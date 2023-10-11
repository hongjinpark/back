package com.example.back.service;


import com.example.back.config.JwtProvider;
import com.example.back.config.auth.PrincipalDetail;
import com.example.back.dto.UserDto;
import com.example.back.dto.UserInfoDto;
import com.example.back.entity.User;
import com.example.back.entity.UserInfo;
import com.example.back.mapper.UserInfoMapper;
import com.example.back.repository.UserInfoRepository;
import com.example.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserInfoService userInfoService;
    private final UserInfoMapper userInfoMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    //계정 생성
    public User createUser(UserDto userDto) {

        userDto.encodePassword(passwordEncoder);
        User user = userDto.toEntity();

        String nickName = user.getNickname();

        //userRepository.findByNickName();

        System.out.println("nickName = " + nickName);
        String status = user.getStatus();
        System.out.println("status = " + status);

        userInfoMapper.createUserInfo(nickName, status);

        //이메일 중복 검사
        validateDuplicateMember(user);
        return userRepository.save(user);
    }

    //계정 정보 수정
    @Transactional
    public Long updateUser(UserDto userDto, PrincipalDetail principalDetail) {
        User user = userRepository.findByEmail(principalDetail.getEmail());

        System.out.println("현재 유저 EMAIL= " + user.getEmail());
        System.out.println("현재 유저 ID = " + user.getId());

        user.update(passwordEncoder.encode(userDto.getPassword()), userDto.getNickname());

        userRepository.save(user);

        if(principalDetail != null) {
            principalDetail.setUser(user); //추가
        }

        return user.getId();
    }

    //계정 삭제
    @Transactional
    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    private void validateDuplicateMember(User user) {

        User findUser = userRepository.findByEmail(user.getEmail());
        if(findUser != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public UserDto loginUser(UserDto userDto) {

        User user = userRepository.findByEmail(userDto.getEmail());
        if(user == null) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");

        }

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        /*User result = userRepository.findUser(userDto.getEmail());*/

        UserDto result = UserDto.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .password(user.getPassword())
                .provider("user")
                .role(user.getRole())
                .token(jwtProvider.createToken(user.getEmail(), user.getRole()))
                .build();

        return result;
    }


    public UserInfo createUserInfo(UserInfoDto userInfoDto, MultipartFile profileImg, PrincipalDetail principalDetail) throws Exception {


        
        Long id = userInfoRepository.findByNickName(principalDetail.getNickName());

        if(id == null) {
            throw new IllegalStateException("존재하지 않는 닉네임입니다.");
        }

        UserInfo userInfo = UserInfoDto.builder()
                .id(id)
                .phoneNum(userInfoDto.getPhoneNum())
                .usrNickName(principalDetail.getNickName())
                .build().toEntity();

        //유저 상세 정보 등록
        userInfoService.saveProfileImg(userInfo, profileImg);

        return userInfoRepository.save(userInfo);
    }


    public UserInfo updateUserInfo(UserInfoDto userInfoDto, MultipartFile profileImg, PrincipalDetail principalDetail) throws Exception {

        UserInfo userInfo = userInfoRepository.findById(principalDetail.getId()).orElseThrow(() -> new IllegalArgumentException("해당 id가 없습니다."));

        userInfo.updateUserInfo(userInfoDto.getPhoneNum(), userInfoDto.getUsrNickName());

        Long profileImgIds = userInfoRepository.countById(principalDetail.getId());

        userInfoService.updateProfileImg(profileImgIds, profileImg);

        return userInfoRepository.save(userInfo);
    }
}
