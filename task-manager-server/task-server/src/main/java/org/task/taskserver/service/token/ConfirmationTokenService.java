package org.task.taskserver.service.token;


import org.task.taskserver.domain.token.ConfirmationToken;
import java.util.Optional;

public interface ConfirmationTokenService {

    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token) ;

    int setConfirmedAt(String token);
}