package dev.krypta.ProjectDataStructure.repository;

import dev.krypta.ProjectDataStructure.entity.ItemFormulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemFormulaRepository extends JpaRepository<ItemFormulaEntity, Long> {

    Boolean existesById(Long id);

}
