package com.kaiq.dscatalog.repositories;

import com.kaiq.dscatalog.entities.Role;
import com.kaiq.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
