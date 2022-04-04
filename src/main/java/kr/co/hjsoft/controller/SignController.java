package kr.co.hjsoft.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.co.hjsoft.config.JwtTokenProvider;
import kr.co.hjsoft.entity.Member;
import kr.co.hjsoft.exception.CEmailSigninFailedException;
import kr.co.hjsoft.model.response.CommonResult;
import kr.co.hjsoft.model.response.SingleResult;
import kr.co.hjsoft.repository.MemberRepository;
import kr.co.hjsoft.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @GetMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String memberEMAIL,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String memberPW) {

        Member member = memberRepository.findBymemberEMAIL(memberEMAIL).orElseThrow(CEmailSigninFailedException::new);
        if (!passwordEncoder.matches(memberPW, member.getPassword()))
            throw new CEmailSigninFailedException();


        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(member.getMemberNICKNAME()), member.getRoles()));
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @GetMapping(value = "/signup")
    public CommonResult signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String memberEMAIL,
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String memberPW,
                               @ApiParam(value = "이름", required = true) @RequestParam String memberNAME,
                               @ApiParam(value = "닉네임", required = true) @RequestParam String memberNICKNAME,
                               @ApiParam(value = "주소", required = true) @RequestParam String memberADDRESS,
                               @ApiParam(value = "전화번호", required = true) @RequestParam String memberPHONE,
                               @ApiParam(value = "성별", required = true) @RequestParam String memberGENDER) {

        memberRepository.save(Member.builder()
                        .memberNAME(memberNAME)
                        .memberNICKNAME(memberNICKNAME)
                        .memberPHONE(memberPHONE)
                        .memberADDRESS(memberADDRESS)
                        .memberGENDER(memberGENDER)
                        .memberEMAIL(memberEMAIL)
                        .memberPW(passwordEncoder.encode(memberPW))
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return responseService.getSuccessResult();
    }
}
