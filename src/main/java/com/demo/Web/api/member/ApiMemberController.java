package com.demo.Web.api.member;


import com.demo.Web.form.member.DupchkForm;
import com.demo.Web.res.ApiResponse;
import com.demo.Web.res.ResCode;
import com.demo.domain.member.svc.MemberSVC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/join")
public class ApiMemberController {

  @Autowired
  MemberSVC memberSVC;

  @PostMapping("/dupchk")
  public ApiResponse<?> dupchk(@RequestBody DupchkForm dupchkForm){
    ApiResponse<?> res = null;
    log.info("email={}", dupchkForm.getEmail());
    if ( memberSVC.existMemberByEmail(dupchkForm.getEmail())) {
      res = ApiResponse.createApiResponse(ResCode.EXIST.getCode(), ResCode.EXIST.name(), null);
    }else{
      res = ApiResponse.createApiResponse(ResCode.NONE.getCode(), ResCode.NONE.name(), null);
    }
    return res;
  }
}
