package com.example.testdemo.repo;

        import com.example.testdemo.domain.PersonInfo;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;


public interface UserRepository extends JpaRepository<PersonInfo,Long> {

    List<PersonInfo> findPersonInfoByPhone(String phone);
    PersonInfo findPersonInfoById(Integer userId);
}
