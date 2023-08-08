package com.kolaykafe.user.service;

import com.kolaykafe.user.dto.UserDTO;
import com.kolaykafe.user.model.User;
import com.kolaykafe.user.repository.IUserRepository;
import com.kolaykafe.user.service.interfaces.IUserCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImp implements IUserCommandService {
    @Autowired
    private IUserRepository _repo;

    @Override
    public UserDTO registerUser(UserDTO obj) {
        User newUser = new User();
        newUser.setFullName(obj.getFullName());
        newUser.setPassword(obj.getPassword());
        newUser.setEmail(obj.getEmail());
        newUser.setPhoneNumber(obj.getPhoneNumber());
        newUser.setMailVerified(false);

        _repo.save(newUser);

        return obj;
    }

    @Override
    public UserDTO updateUser(UserDTO obj) {
        User recentUser = _repo.fingByEmail(obj.getEmail());

        recentUser.setFullName(obj.getFullName());
        recentUser.setPassword(obj.getPassword());
        recentUser.setEmail(obj.getEmail());
        recentUser.setPhoneNumber(obj.getPhoneNumber());
        recentUser.setMailVerified(obj.getMailVerified());

        _repo.save(recentUser);

        return obj;
    }

    @Override
    public boolean verifyEmail(String email) {
        User verifiedMail = _repo.fingByEmail(email);
        verifiedMail.setMailVerified(true);

        _repo.save(verifiedMail);

        return true;
    }
}
