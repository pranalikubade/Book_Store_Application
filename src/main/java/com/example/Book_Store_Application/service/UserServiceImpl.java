package com.example.Book_Store_Application.service;

import com.example.Book_Store_Application.dto.ResponseDTO;
import com.example.Book_Store_Application.dto.UserDTO;
import com.example.Book_Store_Application.exception.UserCustomException;
import com.example.Book_Store_Application.model.User;
import com.example.Book_Store_Application.repo.UserRepo;
import com.example.Book_Store_Application.utility.EmailService;
import com.example.Book_Store_Application.utility.Jwt_Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService_Interface{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private Jwt_Token jwt_token;


    private User checkIfUserExist(String email){
        return userRepo.getUserIDByEmail(email);
    }
                            // :: UserBook - Registration ::  //
    @Override
    public ResponseDTO registerUser(UserDTO userDTO) {
        User data = checkIfUserExist(userDTO.getEmail());

        if(data == null){
            Random random = new Random();
            User registerNewUser = new User(userDTO);
            registerNewUser.setOtp((random.nextInt(9000)+999));
            userRepo.save(registerNewUser);
            emailService.sendEmail(userDTO.getEmail(),"User DataBase Account Verification",
                    "Hey... " + (userDTO.getFirstName()) +
                            "\n\n Your OTP for VERIFICATION :: "+registerNewUser.getOtp());
        }
        return new ResponseDTO("User with same Email ID already exist..", data);
    }
                        // :: UserBook - User Login :: //
    @Override
    public String loginUser(String email,String password) {
        User data = checkIfUserExist(email);
        if(null != data){
            if(data.getPassword().equals(password)){
                String token = jwt_token.createToken(data.getUserId());
                return ":: " + data.getFirstName() + " " + data.getLastName() + " logged in Successfully ::" + "\n\n" +token;
            }
        }
        return " User with Email ID :: " + email + " doesn't exists ";
    }
                    // :: UserBook - Update User Details Using ID :: //
    @Override
    public ResponseDTO updateUserById(UserDTO userDTO, Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.updatedDate(userDTO);
            user.setUpdatedDate(LocalDate.now());
            userRepo.save(user);

            return new ResponseDTO("User Details Updated Successfully....", user);
        } else {
            return new ResponseDTO("User with ID :: " + userId + " doesn't exist", null);
        }
    }
                        // :: UserBook - Get User Details Using  ID :: //
@Override
    public ResponseDTO getUserById(long id) {
        Optional<User> userOptional = userRepo.findById(id);

        if (userOptional.isPresent()) {
            User userData = userOptional.get();
            return new ResponseDTO("Details :: ", userData);
        } else {
            return new ResponseDTO("User with ID :: " + id + " doesn't exist", null);
        }
    }

                    // :: UserBook - Delete User Details Using  ID :: //
    @Override
    public ResponseDTO deleteUserById(Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);

        if (userOptional.isPresent()) {
            userRepo.deleteById(userId);
            return  new ResponseDTO("User with ID  successfully deleted.",userId);
        } else {
            return new ResponseDTO("User with ID  doesn't exist.",userId);
        }
    }

                         // :: UserBook - Verify Using Token ::  //
    @Override
    public String verifyUsingToken(String token) {
        long id = jwt_token.decodeToken(token);
        User data = userRepo.findById(id).orElseThrow(()-> new UserCustomException(" User not found with token :: " + token));;
        if(data.isVerify()){
            return " User is already verified..";
        }
        data.setVerify(true);
        userRepo.save(data);
        return " User verified Successfully..";
    }
}
