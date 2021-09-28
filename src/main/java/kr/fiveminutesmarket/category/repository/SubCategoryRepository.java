package kr.fiveminutesmarket.category.repository;

import kr.fiveminutesmarket.category.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    long countBySubCategoryName(String subCategoryName);
}
