package az.developia.e_commerce_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.e_commerce_project.entity.UserDetailEntity;

public interface UserDetailRepsository extends JpaRepository<UserDetailEntity, Integer> {

}
