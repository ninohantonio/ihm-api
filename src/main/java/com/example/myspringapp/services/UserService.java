package com.example.myspringapp.services;

import ch.qos.logback.classic.encoder.JsonEncoder;
import com.example.myspringapp.dao.UserRepository;
import com.example.myspringapp.dto.Message;
import com.example.myspringapp.dto.UserDto;
import com.example.myspringapp.dto.UserInfo;
import com.example.myspringapp.entities.Animal;
import com.example.myspringapp.entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final Map<String, UserInfo> sessionDataMap = new ConcurrentHashMap<>();

    public ResponseEntity<?> signup(UserDto userDto){
        try{
            if(userRepository.existsByEmail(userDto.getEmail())){
                Message message = new Message();
                message.setMessage("Mail deja Utiliser");
                message.setSuccess(false);
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            User newuser = new User();
            newuser.setEmail(userDto.getEmail());
            newuser.setUsername(userDto.getUsername());
            newuser.setPassword(userDto.getPassword());
            User created = userRepository.save(newuser);
            return new ResponseEntity<>(created, HttpStatusCode.valueOf(200));
        }catch (DataIntegrityViolationException e){
            return new ResponseEntity<>("Erreur d'integrite "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<?> signin(UserDto userDto, HttpSession session){
        try{
            Message message = new Message();
            if(userRepository.existsByEmail(userDto.getEmail())){
                User user = userRepository.findByEmail(userDto.getEmail());
                if(user.getPassword().equals(userDto.getPassword())){
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(user.getId());
                    userInfo.setUsername(user.getUsername());
                    userInfo.setEmail(user.getEmail());
                    setSessionData(user.getEmail(), userInfo);
                    session.setAttribute("loggedInUser", userInfo);
                    return new ResponseEntity<>(userInfo, HttpStatusCode.valueOf(200));
                }else {
                    message.setSuccess(false);
                    message.setMessage("invalid credentials");
                }
            }
            message.setSuccess(false);
            message.setMessage("invalid credentials");
            return new ResponseEntity<>( message, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity<>("Erreur d'insertion " + e.getMessage(), HttpStatusCode.valueOf(500));
        }
    }

    public ResponseEntity<?> userprofile(HttpSession session) {
        UserInfo loggedInUser = (UserInfo) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }
    }

    public Boolean isauth(HttpSession session){
        UserInfo logged = (UserInfo) session.getAttribute("loggedInUser");
        return logged != null;
    }

    public User connecteduser(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("loggedInUser");
        return userRepository.findById(userInfo.getId()).get();
    }

    public void setSessionData(String sessionId, UserInfo userInfo) {
        sessionDataMap.put(sessionId, userInfo);
    }

    public UserInfo getSessionData(String sessionId) {
        return sessionDataMap.get(sessionId);
    }


    public ResponseEntity<?> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("logout successful");
    }
}
