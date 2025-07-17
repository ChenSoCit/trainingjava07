package com.java.TrainningJV.dtos.response;

import java.util.List;

import com.java.TrainningJV.models.User;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserPageResponse extends PageResponseAbstract {
    private List<User> users;
}
