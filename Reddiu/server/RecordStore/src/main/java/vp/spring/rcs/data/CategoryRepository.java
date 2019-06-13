package vp.spring.rcs.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import vp.spring.rcs.model.Category;

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
