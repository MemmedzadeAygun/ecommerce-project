package az.developia.e_commerce_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.e_commerce_project.entity.UserEntity;

public interface UserRepsository extends JpaRepository<UserEntity, String>{

}
