package org.task.taskserver.service.token;


import org.task.taskserver.domain.token.ConfirmationToken;
import org.task.taskserver.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}