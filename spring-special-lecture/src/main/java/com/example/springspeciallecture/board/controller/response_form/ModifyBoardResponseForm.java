package com.example.springspeciallecture.board.controller.response_form;

import com.example.springspeciallecture.board.service.response.ModifyBoardResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ModifyBoardResponseForm {
    final private Long boardId;
    final private String title;
    final private String content;
    final private String nickname;


    public static ModifyBoardResponseForm from(ModifyBoardResponse response) {
        return new ModifyBoardResponseForm(response.getBoardId(), response.getTitle(), response.getContent(), response.getNickname());
    }
}
