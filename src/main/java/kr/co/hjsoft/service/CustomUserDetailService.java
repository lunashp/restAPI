package kr.co.hjsoft.service;

import kr.co.hjsoft.exception.CUserNotFoundException;
import kr.co.hjsoft.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String memberEMAIL) {
        return memberRepository.findBymemberEMAIL(String.valueOf(memberEMAIL)).orElseThrow(CUserNotFoundException::new);
    }
}
