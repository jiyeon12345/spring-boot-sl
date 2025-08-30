package com.example.springspeciallecture.board.service.response;

import com.example.springspeciallecture.board.entity.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListBoardResponse {
    final private List<Board> boardList;
    final private Long totalItems;
    final private  Integer TotalPages;

    public List<Map<String, Object>> getBoardListWithNicknames() {
        return boardList.stream().map(board -> {
            Map<String, Object> boardMap = new HashMap<>();

            boardMap.put("boardId", board.getBoardId());
            boardMap.put("title", board.getTitle());
            boardMap.put("content", board.getContent());
            boardMap.put("nickname", board.getWriter().getNickname());
            boardMap.put("createDate", formatDate(board.getCreateDate()));
            return boardMap;
        }).collect(Collectors.toList());
    }

    private String formatDate(LocalDateTime dateTime) {
        if(dateTime == null) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    public static ListBoardResponse from (List<Board> boardList, Long totalItems, Integer totalPages){
        return new ListBoardResponse(boardList, totalItems, totalPages);
    };

}
