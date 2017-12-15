package com.adamant.dao;

import com.adamant.entity.UserEntity;

public interface UserDao {
    int deleteByPrimaryKey(String id);

    int insert(UserEntity userEntity);

    int insertSelective(UserEntity userEntity);

    UserEntity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserEntity userEntity);

    int updateByPrimaryKey(UserEntity userEntity);
}