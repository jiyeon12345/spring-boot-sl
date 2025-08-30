package com.example.springspeciallecture.board.controller.response_form;

import com.example.springspeciallecture.board.service.response.CreateBoardResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateBoardResponseForm {
    final private String title;
    final private String content;
    final private String nickname;


    public static CreateBoardResponseForm form(CreateBoardResponse response) {
        return new CreateBoardResponseForm(response.getTitle(), response.getContent(), response.getNickname());
    }
}
