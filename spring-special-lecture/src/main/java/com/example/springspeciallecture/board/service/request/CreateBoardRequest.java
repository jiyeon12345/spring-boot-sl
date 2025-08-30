package com.example.springspeciallecture.board.service.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateBoardRequest {
    final private String title;
    final private String content;
    final private String userId;

    public CreateBoardRequest(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
