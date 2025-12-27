package ma.formations.springmvcrestdatajpa.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ma.formations.springmvcrestdatajpa.service.modele.Emp;

/**
 * Spring Data génère automatiquement l'implémentation !
 * Pas besoin d'écrire du SQL pour les méthodes standards.
 */
public interface EmpRepository extends JpaRepository<Emp, Long> {
    List<Emp> findBySalary(Double salary);
    List<Emp> findByFonction(String designation);
    List<Emp> findBySalaryAndFonction(Double salary, String fonction);

    @Query("SELECT e from Emp e where e.salary=(select MAX(salary) as salary FROM Emp)")
    Emp getEmpHavaingMaxSalary();
}