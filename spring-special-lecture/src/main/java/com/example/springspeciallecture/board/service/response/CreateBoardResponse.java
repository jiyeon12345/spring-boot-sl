package com.example.springspeciallecture.board.service.response;

import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.board.entity.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateBoardResponse {
    final private String title;
    final private String content;
    final private String nickname;


    public CreateBoardResponse(Board board, AccountProfile otherAccountProfile) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.nickname = otherAccountProfile.getNickname();
    }

    public static CreateBoardResponse from(Board board){
        return new CreateBoardResponse(board.getTitle(), board.getContent(), board.getWriter().getNickname());
    }
}
