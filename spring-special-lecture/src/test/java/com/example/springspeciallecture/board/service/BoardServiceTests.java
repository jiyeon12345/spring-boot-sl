package com.example.springspeciallecture.board.service;


import com.example.springspeciallecture.account.entity.*;
import com.example.springspeciallecture.account.repository.AccountRepository;
import com.example.springspeciallecture.account_profile.entity.AccountProfile;
import com.example.springspeciallecture.account_profile.repository.AccountProfileRepository;
import com.example.springspeciallecture.board.entity.Board;
import com.example.springspeciallecture.board.repository.BoardRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

public class BoardServiceTests {

    @Mock
    BoardRepository boardRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountProfileRepository accountProfileRepository;

    @InjectMocks
    private BoardServiceImpl boardService;

    private Account account;

    private AccountProfile accountProfile;

    private List<Board> boardList;

    public static Account createAccountWithId(Long id) {
        AccountRoleType roleType = new AccountRoleType(RoleType.NORMAL);
        AccountLoginType loginType = new AccountLoginType(LoginType.KAKAO);

        Account account = new Account(roleType, loginType);
        ReflectionTestUtils.setField(account, "id", id);

        return account;
    }

    public static AccountProfile createAccountProfileWithIdAndAccount(Long id, Account account) {
        AccountProfile profile = new AccountProfile(account, "test_account", "test@test.com");
        ReflectionTestUtils.setField(profile, "id", id);

        return profile;
    }
}
