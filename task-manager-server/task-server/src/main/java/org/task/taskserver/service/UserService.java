package org.task.taskserver.service;

import org.task.taskserver.domain.User;
import org.task.taskserver.dto.UserDto;
import org.task.taskserver.exception.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {
    User register(String firstName, String lastName, String username, String email, String password, String siteURL) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, IOException;

    void passwordReset(String email, String siteURL) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, IOException, EmailNotFoundException;

    String changePassword(String code, String password) throws UserNotFoundException, UsernameExistException, EmailExistException, MessagingException, IOException, EmailNotFoundException, TokenNotFoundException;

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    String verify(String verificationCode) throws IOException, MessagingException, TokenNotFoundException;

    boolean resend(String code, String verificationCode) throws IOException, MessagingException, TokenNotFoundException;

    Page<UserDto> getUsersList(Pageable pageable, String userId, String username, String firstName, String lastName, String email, Boolean status, Boolean locked);

    List<UserDto> fetchUsernames();

    List<UserDto> fetchFirstNames();

    List<UserDto> fetchLastNames();

    List<UserDto> fetchEmails();

    long createUser(UserDto dto, String siteURL) throws UserNotFoundException, UsernameExistException, EmailExistException, IOException, MessagingException;

    boolean updateUser(UserDto dto);

    UserDto getUserById(long id);

    boolean deleteUser(Long id);
}
