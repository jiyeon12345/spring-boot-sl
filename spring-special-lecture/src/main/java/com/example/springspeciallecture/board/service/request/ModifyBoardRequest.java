package com.example.springspeciallecture.board.service.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ModifyBoardRequest {
    final private Long boardId;
    private final String title;
    private final String content;
    private final String nickname;

    public ModifyBoardRequest(Long boardId, String title, String content, String nickname) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }
}
