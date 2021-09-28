package kr.fiveminutesmarket.category.repository;

import kr.fiveminutesmarket.category.domain.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {

    long countByMainCategoryName(String mainCategoryName);
}
