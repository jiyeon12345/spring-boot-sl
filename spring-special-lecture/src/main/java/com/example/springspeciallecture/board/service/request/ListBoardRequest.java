package com.example.springspeciallecture.board.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class ListBoardRequest {
    final private Integer page;
    final private Integer perPage;

    public ListBoardRequest(Integer page, Integer perPage) {
        this.page = page;
        this.perPage = perPage;
    }
}
