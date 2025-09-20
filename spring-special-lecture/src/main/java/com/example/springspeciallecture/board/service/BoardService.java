package com.example.springspeciallecture.board.service;

import com.example.springspeciallecture.board.service.request.CreateBoardRequest;
import com.example.springspeciallecture.board.service.request.ListBoardRequest;
import com.example.springspeciallecture.board.service.request.ModifyBoardRequest;
import com.example.springspeciallecture.board.service.response.CreateBoardResponse;
import com.example.springspeciallecture.board.service.response.ListBoardResponse;
import com.example.springspeciallecture.board.service.response.ModifyBoardResponse;

public interface BoardService {
    ListBoardResponse list(ListBoardRequest request);

    CreateBoardResponse registerBoard(CreateBoardRequest any);

    ModifyBoardResponse modifyBoard(ModifyBoardRequest toBoardRequest);
}
