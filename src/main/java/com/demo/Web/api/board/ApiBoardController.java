package com.demo.Web.api.board;

import com.demo.domain.board.svc.NoticeBoardSVC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/board")
public class ApiBoardController {

  @Autowired
  NoticeBoardSVC noticeBoardSVC;

  @GetMapping("/hit")
  public String plusHit(@RequestParam("noticeboardId") Long noticeboardId) {

    noticeBoardSVC.calucateHit(noticeboardId);
    return "Success";
  }
}
