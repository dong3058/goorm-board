package com.example.demo.Service;


import com.example.demo.*;
import com.example.demo.Repository.BoardCommentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MainService {

    private final BoardCommentRepository boardCommentRepository;


    public void SaveBoard(Board board){
        boardCommentRepository.SaveBoard(board);
    }
    public void Deleteboard(Long id){
        boardCommentRepository.DeleteBoard(id);
    }
    public void Deletecomment(Long id){
        boardCommentRepository.DeleteComment(id);
    }

    public List<BoardToShow> GetBoardList(Long id){

        List<BoardToShow> board=boardCommentRepository.GetBoardList(id);



        return board;
    }
    public List<BoardToShow> GetBoardListByPagingNum(Long id){

        List<BoardToShow> board=boardCommentRepository.GetBoardListByPagingNum(id);

        return board;
    }

    public void UpdateBoardService(BoardDto boardDto,Long id){
        boardCommentRepository.UpdateBoard(boardDto,id);
    }

    public BoardToShowOne SerachBoard(Long id){


        return boardCommentRepository.SearchBoard(id);
    }

    public void SaveComment(Comment comment,Long id){
            boardCommentRepository.SaveComment(comment,id);
    }

    public Comment SearchComment(Long id){

        return boardCommentRepository.SearchComment(id);
    }
    public void UpdateComment(String maintext,Long id){
        boardCommentRepository.UpdateComment(maintext,id);
    }

    public ResponseEntity<List<Comment>> GetCommentList(Long id){

        return new ResponseEntity<>(boardCommentRepository.GetCommentList(id), HttpStatus.OK);
    }

    
}
