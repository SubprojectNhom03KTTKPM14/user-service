package comjava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import comjava.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
}
