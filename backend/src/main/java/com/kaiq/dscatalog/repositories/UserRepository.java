package com.kaiq.dscatalog.repositories;

import com.kaiq.dscatalog.entities.Product;
import com.kaiq.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
