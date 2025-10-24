package dev.krypta.ProjectDataStructure.repository;

import dev.krypta.ProjectDataStructure.entity.FormulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormulaRepository extends JpaRepository<FormulaEntity, Long> {

    Boolean existesById(Long id);

}
