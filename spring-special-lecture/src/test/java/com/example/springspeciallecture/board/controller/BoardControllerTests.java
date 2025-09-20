package com.example.springspeciallecture.board.controller;

import com.example.springspeciallecture.account.entity.Account;
import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.board.controller.request_form.CreateBoardRequestForm;
import com.example.springspeciallecture.board.controller.request_form.ListBoardRequestForm;
import com.example.springspeciallecture.board.controller.request_form.ModifyBoardRequestForm;
import com.example.springspeciallecture.board.controller.response_form.CreateBoardResponseForm;
import com.example.springspeciallecture.board.controller.response_form.ListBoardResponseForm;
import com.example.springspeciallecture.board.controller.response_form.ModifyBoardResponseForm;
import com.example.springspeciallecture.board.entity.Board;
import com.example.springspeciallecture.board.service.BoardService;
import com.example.springspeciallecture.board.service.BoardServiceTests;
import com.example.springspeciallecture.board.service.request.ListBoardRequest;
import com.example.springspeciallecture.board.service.response.CreateBoardResponse;
import com.example.springspeciallecture.board.service.response.ListBoardResponse;
import com.example.springspeciallecture.board.service.response.ModifyBoardResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //JUnit 5에서 Mockito를 사용할 때 필요한 설정 어노테이션. MockitoExtension.class를 지정하면 Mockito 관련 기능을 자동으로 적용
public class BoardControllerTests {

    @InjectMocks // @Mock으로 만든 목들을 SUT에 주입해 줍니다.
    private BoardController boardController; // 컨트롤러 테스트 할 때 필요한 진짜 컨트롤러 객체

    @Mock //의존성(협력 객체)을 가짜(Mock)로 만들어 주는 어노테이션.
    private BoardService boardService; // 컨트롤러가 잘 되는지 확인해주는 용으로 만드는 가짜

    /*
    * injectMock과 Mock 의 차이
    *
    * */
    @Test
    void 게시글_목록_조회() {
        Account otherAccount = BoardServiceTests.createAccountWithId(1L);
        AccountProfile otherAccountProfile = BoardServiceTests.createAccountProfileWithIdAndAccount(100L, otherAccount);

        // 게시물이 무엇을 표현할 것인가
        Board board = new Board("테스트 제목", otherAccountProfile, "테스트 목적의 내용");
        ReflectionTestUtils.setField(board, "boardId", 1L);
        ReflectionTestUtils.setField(board, "createDate", LocalDateTime.now());

        //위에서 만든 board object를 list 형태로 변환
        List<Board> boardList = List.of(board);

        // 기대값 -> ~ Response Dto 가 필요함
        Long expectedTotalItems = 1L; // 전체 데이터 수량
        Integer expectedTotalPages = 1; //페이징 수량

        ListBoardResponse response = new ListBoardResponse(boardList, expectedTotalItems, expectedTotalPages);

        //boardService에 list가 호출되면 response 데이터를 return 하라는 Mockito의 stubbing (행동 정의) 구문
        when(boardService.list(any())).thenReturn(response);

        //외부에서 들어올 데이터 폼 -> ListBoardRequestForm
        Integer requestPageNumber = 1;
        Integer requestPageItemsNumber = 10;
        ListBoardRequestForm requestForm = new ListBoardRequestForm(requestPageNumber, requestPageItemsNumber);

        // controller에서 받아 return할 데이터 폼 -> ListBoardResponseForm
        ListBoardResponseForm responseForm = boardController.boardList(requestForm);

        assertEquals(expectedTotalItems, responseForm.getTotalItems());
        assertEquals(expectedTotalPages, responseForm.getTotalPages());
        assertEquals(1, responseForm.getBoardList().size());

        Map<String, Object> boardMap = responseForm.getBoardList().get(0);
        assertEquals(board.getBoardId(), boardMap.get("boardId"));
        assertEquals(board.getTitle(), boardMap.get("title"));
        assertEquals(board.getContent(), boardMap.get("content"));
        assertEquals(otherAccountProfile.getNickname(), boardMap.get("nickname"));
    }

    @Test
    void 게시글_저장() {
        Account otherAccount = BoardServiceTests.createAccountWithId(1L);
        AccountProfile otherAccountProfile = BoardServiceTests.createAccountProfileWithIdAndAccount(100L, otherAccount);

        // 게시물이 무엇을 표현할 것인가
        Board board = new Board("제목", otherAccountProfile, "내용");
        ReflectionTestUtils.setField(board, "boardId", 1L);
        ReflectionTestUtils.setField(board, "createDate", LocalDateTime.now());

        //service가 createBoard 메소드를 호출한 이후 return되는 dto가 CreateBoardResponse가 맞는지 확인
        CreateBoardResponse response = new CreateBoardResponse(board, otherAccountProfile);
        when(boardService.registerBoard(any())).thenReturn(response);

        //사용자가 작성 할 제목, 내용을 입력한다 (사용자 정보도 함께 전달)
        CreateBoardRequestForm form = new CreateBoardRequestForm("제목", "내용", "사용자");
        CreateBoardResponseForm result = boardController.registerBoard(form);

        assertEquals("제목", result.getTitle());
        assertEquals("내용", result.getContent());

    }

    @Test
    void 게시물_수정() {
        Account otherAccount = BoardServiceTests.createAccountWithId(1L);
        AccountProfile otherAccountProfile = BoardServiceTests.createAccountProfileWithIdAndAccount(100L, otherAccount);

        // 게시물이 무엇을 표현할 것인가
        Board board = new Board("수정된 제목", otherAccountProfile, "수정된 내용");
        ReflectionTestUtils.setField(board, "boardId", 1L);
        ReflectionTestUtils.setField(board, "createDate", LocalDateTime.now());
        ReflectionTestUtils.setField(board, "modifyDate", LocalDateTime.now());

        //외부에서 가지고 오는 데이터 정보
        ModifyBoardRequestForm requestForm = new ModifyBoardRequestForm(1L,"수정된 제목", "수정된 내용", "사용자");

        //controller가 modifyBoard 메소드를 호출 시 ModifyBoardResponse dto가 return 되는지 확인
        ModifyBoardResponse response =
                new ModifyBoardResponse(1L, "수정된 제목", "수정된 내용", "사용자");

        when(boardService.modifyBoard(any())).thenReturn(response);

        //controller에서 modifyBoard 작업
        ModifyBoardResponseForm responseForm = boardController.modifyBoard(requestForm);

        assertEquals("수정된 제목", responseForm.getTitle());
        assertEquals("수정된 내용", responseForm.getContent());
        assertEquals("사용자", responseForm.getNickname());
    }
}
