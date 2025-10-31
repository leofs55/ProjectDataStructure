package dev.krypta.ProjectDataStructure.repository;

import dev.krypta.ProjectDataStructure.entity.ItemFormulaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemFormulaRepository extends JpaRepository<ItemFormulaEntity, Long> {

    List<ItemFormulaEntity> findByNomeFormula(String nomeFormula);

    Optional<ItemFormulaEntity> findByNome(String nomeItem);

}
