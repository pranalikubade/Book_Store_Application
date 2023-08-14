package com.example.Book_Store_Application.repo;

import com.example.Book_Store_Application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    //@Query(value = "select * from book_store.user_data where name = :name",nativeQuery = true)
    User getUserIDByEmail(String email);
}
