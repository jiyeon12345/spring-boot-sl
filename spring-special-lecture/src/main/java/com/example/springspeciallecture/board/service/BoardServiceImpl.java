package com.example.springspeciallecture.board.service;

import com.example.springspeciallecture.account.entity.Account;
import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.account_profile.service.AccountProfileService;
import com.example.springspeciallecture.board.service.request.CreateBoardRequest;
import com.example.springspeciallecture.board.service.request.ListBoardRequest;
import com.example.springspeciallecture.board.service.request.ModifyBoardRequest;
import com.example.springspeciallecture.board.service.response.CreateBoardResponse;
import com.example.springspeciallecture.board.service.response.ListBoardResponse;
import com.example.springspeciallecture.board.entity.Board;
import com.example.springspeciallecture.board.repository.BoardRepository;
import com.example.springspeciallecture.board.service.response.ModifyBoardResponse;
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
        Optional<AccountProfile> accountProfile = accountProfileService.loadProfileByNickname(request.getNickname());

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

    @Override
    public ModifyBoardResponse modifyBoard(ModifyBoardRequest request) {
        /* account profile 존재 유무 확인 */
        Optional<AccountProfile> accountProfile = accountProfileService.loadProfileByNickname(request.getNickname());

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

        /* board  존재 유무 */
       boolean isExistBoardId = boardRepository.existsById(request.getBoardId());

//       if(! isExistBoardId) {
//           throw new RuntimeException("해당하는 게시판 정보가 없습니다");
//       }
//        if( isExistBoardId && ! (profile.getNickname().equals(request.getNickname()))) {
//            throw new RuntimeException("게시판 수정 권한이 없습니다.");
//        }

        Board board = new Board(request.getBoardId(), request.getTitle(), profile, request.getContent());
        boardRepository.save(board);

        return ModifyBoardResponse.from(board.getBoardId(), board.getTitle(), board.getContent(), board.getWriter().getNickname());
    }
}
