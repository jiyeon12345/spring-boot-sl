package com.example.springspeciallecture.board.controller;

import com.example.springspeciallecture.board.controller.request_form.CreateBoardRequestForm;
import com.example.springspeciallecture.board.controller.request_form.ListBoardRequestForm;
import com.example.springspeciallecture.board.controller.response_form.CreateBoardResponseForm;
import com.example.springspeciallecture.board.service.response.CreateBoardResponse;
import com.example.springspeciallecture.board.service.response.ListBoardResponse;
import com.example.springspeciallecture.board.controller.response_form.ListBoardResponseForm;
import com.example.springspeciallecture.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    final private BoardService boardService;

    @GetMapping("/list")
    public ListBoardResponseForm boardList(@ModelAttribute ListBoardRequestForm requestForm) {
        log.info("boardList() -> requestForm : {}", requestForm);

        ListBoardResponse response = boardService.list(requestForm.toListBoardRequest());
        return ListBoardResponseForm.from(
                List.of(response),
                response.getTotalItems(),
                response.getTotalPages()
        );
    }

    @PostMapping("/register")
    public CreateBoardResponseForm registerBoard(CreateBoardRequestForm requestForm) {
        log.info("registerBoard() -> requestForm: {}", requestForm);

        CreateBoardResponse response = boardService.registerBoard(requestForm.toBoardRequest());
        return CreateBoardResponseForm.form(response);
    }
}
