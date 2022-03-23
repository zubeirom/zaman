//package com.example.zaman_desktop.fx.converter;
//
//import com.example.zaman_desktop.enums.UserTyp;
//import com.example.zaman_desktop.fx.models.UserFx;
//import com.example.zaman_desktop.models.User;
//
//import java.security.Timestamp;
//
//public class UserConverter {
//
//    public UserConverter() {
//    }
//
//
//    public static UserFx toUserFx(User user) {
//
//        UserFx userFx = new UserFx();
//
//        userFx.setFirstName(user.getFirstName());
//        userFx.setLastName(user.getLastName());
//        userFx.setPassword(user.getPassword());
//        userFx.setEmail(user.getEmail());
//        userFx.setType(user.getType());
//        userFx.setCreatedAt(user.getCreatedAt());
//        userFx.setUpdateAt(user.getUpdateAt());
//
//        return userFx;
//    }
//
//
//    public static User toUser(UserFx userFx) {
//        String  firstName = userFx.getFirstName();
//        String  lastName = userFx.getLastName();
//        String  passwoed = userFx.getPassword();
//        String  email = userFx.getEmail();
//        UserTyp userTyp = userFx.getType();
//        Timestamp createdAt = userFx.getCreatedAt();
//        Timestamp updateAt =userFx.getUpdateAt();
//
//
//        User user = new User();
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setEmail(email);
//        user.setPassword(passwoed);
//        user.setEmail(email);
//        user.setType(userTyp);
//        user.setCreatedAt(createdAt);
//        user.setUpdateAt(updateAt);
//        return  user ;
//
//        }
//
//}