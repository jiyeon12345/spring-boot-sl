package com.example.springspeciallecture.board.service;

import com.example.springspeciallecture.board.service.request.ListBoardRequest;
import com.example.springspeciallecture.board.service.response.ListBoardResponse;
import com.example.springspeciallecture.board.entity.Board;
import com.example.springspeciallecture.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    final private BoardRepository boardRepository;
    @Override
    public ListBoardResponse list(ListBoardRequest request) {
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getPerPage());

        Page<Board> boardPage = boardRepository.findAllWithWriter(pageRequest);

        return ListBoardResponse.from(
                boardPage.getContent(),
                boardPage.getTotalElements(),
                boardPage.getTotalPages()
        );
    }
}
