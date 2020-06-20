package com.softobt.quizmanager.lib.common.repository;

import com.softobt.asgardian.control.repositories.AsgardianUserRepository;
import com.softobt.quizmanager.lib.common.models.QuizAdmin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * @author aobeitor
 * @since 6/10/20
 */
@Repository
public interface AdminRepository extends AsgardianUserRepository<QuizAdmin,Long> {
    @Query("UPDATE QuizAdmin q set q.lastLogin = :now where q.username = :username and q.domain.name = :domain")
    void updateLastLogin(@Param("now") LocalDateTime now,@Param("username")String username, @Param("domain")String domain);
}
