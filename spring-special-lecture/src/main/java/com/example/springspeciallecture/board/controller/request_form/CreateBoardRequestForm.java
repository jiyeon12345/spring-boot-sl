package com.example.springspeciallecture.board.controller.request_form;

import com.example.springspeciallecture.board.service.request.CreateBoardRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class CreateBoardRequestForm {
    final private String title;
    final private String content;
    final private String userId;


    public CreateBoardRequest toBoardRequest() {
       return new CreateBoardRequest(title, content, userId);
    }
}
