package com.example.springspeciallecture.board.controller.request_form;

import com.example.springspeciallecture.board.service.request.ModifyBoardRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ModifyBoardRequestForm {
    final private Long boardId;
    final private String title;
    final private String content;
    final private String nickname;


    public ModifyBoardRequest toBoardRequest() {
        return new ModifyBoardRequest(boardId, title, content, nickname);
    }
}
