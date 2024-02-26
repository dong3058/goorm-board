package com.example.demo.Repository;


import com.example.Test;
import com.example.demo.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardCommentRepository {

    private final EntityManager em;



    public void SaveBoard(Board board){
        LocalDateTime localDateTime=LocalDateTime.now();
        board.setCreate_dt(localDateTime);
        board.setDeleted("false");
        em.persist(board);
    }
    public BoardToShowOne SearchBoard(Long id){
        try{
        String query="select b from Board b join fetch b.commentList where b.id=:id and b.deleted=:check ";
        Board board=em.createQuery(query,Board.class)
                .setParameter("id",id)
                .setParameter("check","false")
                .getSingleResult();

       List<CommentToShow> cmt=board.getCommentList().stream().filter(x->x.getDeleted().equals("false"))
               .map(x->new CommentToShow(x.getId(),x.getMaintext(),x.getCreate_dt()))
               .collect(Collectors.toList());
       cmt.sort(new CommentCompare());


       return new BoardToShowOne(board.getTitle(), board.getMaintext(), board.getCreate_dt(),cmt);}
        catch(NoResultException n){
            String query="select b from Board b where b.id=:id and b.deleted=:check";
            Board board=em.createQuery(query,Board.class)
                    .setParameter("id",id)
                    .setParameter("check","false")
                    .getSingleResult();
            List<CommentToShow> cmt=new ArrayList<>();
            board.getCommentList();
            return new BoardToShowOne(board.getTitle(), board.getMaintext(), board.getCreate_dt(),
                    cmt);

        }

    }

    public void DeleteBoard(Long id){
        String query="select b from Board b where b.id=:id";
        Board board=em.createQuery(query,Board.class)
                .setParameter("id",id)
                .getSingleResult();
        board.getCommentList().stream().forEach(x->x.setDeleted("true"));
        board.setDeleted("true");

    }
    public void UpdateBoard(BoardDto boardDto,Long id){
        String query="select b from Board b where bl.id=:id";
        Board board=em.createQuery(query,Board.class)
                        .setParameter("id",id)
                                .getSingleResult();

        board.setMaintext(boardDto.getMaintext());
        board.setCreate_dt(LocalDateTime.now());

        if(!board.getTitle().equals(boardDto.getTitle())){
            board.setTitle(boardDto.getTitle());
        }
    }

    public void SaveComment(Comment comment,Long id){
        LocalDateTime localDateTime=LocalDateTime.now();
        comment.setCreate_dt(localDateTime);
        String query="select b from Board b where b.id=:id";
        Board board=em.createQuery(query,Board.class)
                .setParameter("id",id)
                .getSingleResult();
        comment.setBoard(board);
        comment.setDeleted("false");
        em.persist(comment);


    }
    public Comment SearchComment(Long id){
        String query="select c from Comment c where c.id=:id and c.deleted=:check";
        Comment c=em.createQuery(query,Comment.class)
                .setParameter("id",id)
                .setParameter("check","false")
                .getSingleResult();
        return c;
    }
    public void UpdateComment(String maintext,Long id){
        Comment c=SearchComment(id);
        c.setMaintext(maintext);
    }
    public void DeleteComment(Long id){
        String query="select c from Comment c where c.id=:id";
        Comment comment=em.createQuery(query,Comment.class)
                .setParameter("id",id)
                .getSingleResult();
        comment.setDeleted("true");

    }

    public List<BoardToShow> GetBoardList(Long id){
        String query="select new BoardToShow(b.id,b.title,b.create_dt) from Board b where b.deleted=:check and b.id>:cursor";
        List<BoardToShow> boards=em.createQuery(query,BoardToShow.class)
                .setParameter("check","false")
                .setParameter("cursor", id)
                .setMaxResults(5)
                .getResultList();
        log.info("boards:{}",boards);
        //이건 커서 방식의 페이징 기능으로 세션의 현재 클릭한 게시물을 id값으로 저장.

        boards.sort(new BoardCompare());
        return boards;
    }

    public List<BoardToShow> GetBoardListByPagingNum(Long id){
        String query="select new BoardToShow(b.id,b.title,b.create_dt) from Board b where b.deleted=:check";
        List<BoardToShow> boards=em.createQuery(query,BoardToShow.class)
                .setParameter("check","false")
                .setFirstResult((int)(id-1)*5)
                .setMaxResults(5)
                .getResultList();
        log.info("boards:{}",boards);
        //이건 offset 방식으로 볼려는 페이지를 숫자로 받아서 di값으로 requestparam으로 받아서 적당히 넘겨주는것.

        boards.sort(new BoardCompare());
        return boards;
    }
    public List<Comment> GetCommentList(Long id){
        String query="select c from Comment c join c.board b where b.id=:id and c.deleted=:check";

        List<Comment> commentlist=em.createQuery(query,Comment.class)
                .setParameter("id",id)
                .setParameter("check","false")
                .getResultList();



        return commentlist;
    }

}
