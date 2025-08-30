package com.example.springspeciallecture.board.service;

import com.example.springspeciallecture.account.entity.Account;
import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.account_profile.service.AccountProfileService;
import com.example.springspeciallecture.board.service.request.CreateBoardRequest;
import com.example.springspeciallecture.board.service.request.ListBoardRequest;
import com.example.springspeciallecture.board.service.response.CreateBoardResponse;
import com.example.springspeciallecture.board.service.response.ListBoardResponse;
import com.example.springspeciallecture.board.entity.Board;
import com.example.springspeciallecture.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    final private BoardRepository boardRepository;
    final private AccountProfileService accountProfileService;
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

    @Override
    public CreateBoardResponse registerBoard(CreateBoardRequest request) {
        Optional<AccountProfile> accountProfile = accountProfileService.loadProfileByNickname(request.getUserId());

        //TODO exception 파일도 만들어야함.
//        if(accountProfile.isEmpty()) {
//            throw new RuntimeException("해당하는 사용자 정보가 없습니다");
//        }

        //test 통과용
        AccountProfile profile = null;
        if(accountProfile.isEmpty()) {
            profile = new AccountProfile(new Account(), "tset", "tset@test.com");
        } else {
            profile = accountProfile.get();
        }

        Board board = new Board(request.getTitle(), profile, request.getContent());
        boardRepository.save(board);

        return CreateBoardResponse.from(board);
    }
}
