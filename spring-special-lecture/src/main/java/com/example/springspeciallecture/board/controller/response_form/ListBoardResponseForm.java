package com.example.springspeciallecture.board.controller.response_form;

import com.example.springspeciallecture.board.service.response.ListBoardResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListBoardResponseForm {
    final private List<Map<String, Object>> boardList;
    final private Long totalItems;
    final private Integer totalPages;

    public static ListBoardResponseForm from (List<ListBoardResponse> boardResponses, Long totalItems, Integer totalPages) {
        List<Map<String, Object>> combinedBoardList = boardResponses.stream()
                .flatMap(response -> response.getBoardListWithNicknames().stream())
                .collect(Collectors.toList());
        return new ListBoardResponseForm(combinedBoardList, totalItems, totalPages);
    }
}
