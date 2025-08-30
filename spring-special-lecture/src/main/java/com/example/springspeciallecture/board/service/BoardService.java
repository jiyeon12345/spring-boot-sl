package com.example.springspeciallecture.board.service;

import com.example.springspeciallecture.board.service.request.ListBoardRequest;
import com.example.springspeciallecture.board.service.response.ListBoardResponse;

public interface BoardService {
    ListBoardResponse list(ListBoardRequest request);
}
