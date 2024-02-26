package com.example.demo.Controller;

import com.example.demo.*;
import com.example.demo.Repository.BoardCommentRepository;
import com.example.demo.Service.MainService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;
    @PostMapping("/save")
    public String Write(@ModelAttribute Board board){
        mainService.SaveBoard(board);

        return "ok";
    }
    @PostMapping("/board")
    public ResponseEntity<BoardToShowOne> Board(@RequestParam(name="id") Long id, HttpServletRequest req){

        BoardToShowOne board=mainService.SerachBoard(id);
        HttpSession session=req.getSession();
        session.setAttribute("id",id);

       return new ResponseEntity<>(board,HttpStatus.OK);
    }
    @PostMapping("/boardupdate")
    public void BoardUpdate(@ModelAttribute BoardDto boardDto,HttpServletRequest req){

        mainService.UpdateBoardService(boardDto,GetId(req));
    }
    @PostMapping("/boardelete")
    public void BoardDelete(HttpServletRequest req){

        mainService.Deleteboard(GetId(req));
    }
    @PostMapping("/commentdelete")
    public void CommentDelete(@RequestParam(name="id") Long id){

        mainService.Deletecomment(id);
    }

    @PostMapping("/boardlist")
    public ResponseEntity<List<BoardToShow>> BoardList(HttpServletRequest req){
        HttpSession session=req.getSession(false);
        if(session==null){
            session=req.getSession();
            session.setAttribute("id",0L);
        }
        List<BoardToShow> board=mainService.GetBoardList((Long) session.getAttribute("id"));
        return new ResponseEntity<>(board, HttpStatus.OK);
    }


    @PostMapping("/boardlistbypaging")
    public ResponseEntity<List<BoardToShow>> BoardListByPagingNum(@RequestParam(name="paging_num") Long paging_num){

        List<BoardToShow> board=mainService.GetBoardListByPagingNum(paging_num);
        return new ResponseEntity<>(board, HttpStatus.OK);
    }




    @PostMapping("/comment")
    public void SaveComment(@ModelAttribute Comment comment,HttpServletRequest req){
        mainService.SaveComment(comment,GetId(req));
    }
    @PostMapping("/commentupdate")
    public void UpdateComment(@RequestParam(name="maintext") String maintext,@RequestParam(name="id") Long id){
        mainService.UpdateComment(maintext,id);
    }
    @PostMapping("/commentlist")
    public ResponseEntity<List<Comment>> CommentList(HttpServletRequest req){
        return mainService.GetCommentList(GetId(req));
    }


    public Long GetId(HttpServletRequest req){
        HttpSession session=req.getSession(false);

        return (Long) session.getAttribute("id");

    }

}
