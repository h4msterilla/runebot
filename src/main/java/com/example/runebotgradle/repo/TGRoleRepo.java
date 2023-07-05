package com.example.runebotgradle.repo;

import com.example.runebotgradle.model.TGRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TGRoleRepo extends CrudRepository<TGRole,Long> {

    List<TGRole> findByUser_UserID(Long userID);

}
