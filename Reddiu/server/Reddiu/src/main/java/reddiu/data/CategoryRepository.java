package reddiu.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import reddiu.model.Category;

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
