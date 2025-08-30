package com.example.springspeciallecture.board.controller;

import com.example.springspeciallecture.board.controller.request_form.ListBoardRequestForm;
import com.example.springspeciallecture.board.service.response.ListBoardResponse;
import com.example.springspeciallecture.board.controller.response_form.ListBoardResponseForm;
import com.example.springspeciallecture.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
