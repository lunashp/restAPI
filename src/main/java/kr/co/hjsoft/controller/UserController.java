package kr.co.hjsoft.controller;


import io.swagger.annotations.*;
import kr.co.hjsoft.entity.Member;
import kr.co.hjsoft.exception.CUserNotFoundException;
import kr.co.hjsoft.model.response.CommonResult;
import kr.co.hjsoft.model.response.ListResult;
import kr.co.hjsoft.model.response.SingleResult;
import kr.co.hjsoft.repository.MemberRepository;
import kr.co.hjsoft.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"2. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private final MemberRepository memberRepository;
    private final ResponseService responseService; // 결과를 처리할 Service

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "/members")
    public ListResult<Member> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(memberRepository.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "회원이메일로 회원을 조회한다")
    @GetMapping(value = "/member")

    public SingleResult<Member> findUserById(@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberEMAIL = authentication.getName();
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(memberRepository.findBymemberEMAIL(memberEMAIL).orElseThrow(CUserNotFoundException::new));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/member")
    public SingleResult<Member> modify(
            @ApiParam(value = "회원이름", required = true) @RequestParam String memberNAME,
            @ApiParam(value = "회원주소", required = true) @RequestParam String memberADDRESS,
            @ApiParam(value = "회원전화번호", required = true) @RequestParam String memberPHONE,
            @ApiParam(value = "회원성별", required = true) @RequestParam String memberGENDER) {
        Member member = Member.builder()
                .memberNAME(memberNAME)
                .memberADDRESS(memberADDRESS)
                .memberPHONE(memberPHONE)
                .memberGENDER(memberGENDER)
                .build();
        return responseService.getSingleResult(memberRepository.save(member));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원별명(NICKNAME)으로 회원정보를 삭제한다")
    @DeleteMapping(value = "/member/{NICKNAME}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable String memberNICKNAME) {
        memberRepository.deleteBymemberNICKNAME(memberNICKNAME);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}
