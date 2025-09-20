package com.example.springspeciallecture.board.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ModifyBoardResponse {
    final private Long boardId;
    final private String title;
    final private String content;
    final private String nickname;

    public static ModifyBoardResponse from(Long boardId, String title, String content, String nickname) {
        return new ModifyBoardResponse(boardId, title, content, nickname);
    }
}
