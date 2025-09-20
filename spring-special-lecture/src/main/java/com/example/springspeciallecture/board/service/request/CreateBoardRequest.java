package com.example.springspeciallecture.board.service.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateBoardRequest {
    final private String title;
    final private String content;
    final private String nickname;

    public CreateBoardRequest(String title, String content, String nickname) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }
}
