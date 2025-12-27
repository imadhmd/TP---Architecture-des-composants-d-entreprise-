package ma.formations.springmvcrestdatajpa.controller.rest;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ma.formations.springmvcrestdatajpa.domaine.EmpVo;
import ma.formations.springmvcrestdatajpa.service.IService;

@RestController
public class EmpRestController {

    @Autowired
    private IService service;

    /**
     * GET tous les employés : http://localhost:8080/rest/emp
     */
    @GetMapping(value = "/rest/emp", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public List<EmpVo> getAll() {
        return service.getEmployees();
    }

    /**
     * GET un employé par ID : http://localhost:8080/rest/emp/1
     */
    @GetMapping(value = "/rest/emp/{id}")
    public ResponseEntity<Object> getEmpById(@PathVariable(value = "id") Long empVoId) {
        EmpVo empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("employee doesn't exist", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(empVoFound, HttpStatus.OK);
    }

    /**
     * POST créer un employé : http://localhost:8080/rest/emp
     */
    @PostMapping(value = "/rest/emp")
    public ResponseEntity<Object> createEmp(@Valid @RequestBody EmpVo empVo) {
        service.save(empVo);
        return new ResponseEntity<>("employee is created successfully", HttpStatus.CREATED);
    }

    /**
     * PUT modifier un employé : http://localhost:8080/rest/emp/1
     */
    @PutMapping(value = "/rest/emp/{id}")
    public ResponseEntity<Object> updateEmp(@PathVariable(name = "id") Long empVoId,
                                            @RequestBody EmpVo empVo) {
        EmpVo empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("employee doesn't exist", HttpStatus.NOT_FOUND);
        empVo.setId(empVoId);
        service.save(empVo);
        return new ResponseEntity<>("Employee is updated successfully", HttpStatus.OK);
    }

    /**
     * DELETE supprimer un employé : http://localhost:8080/rest/emp/1
     */
    @DeleteMapping(value = "/rest/emp/{id}")
    public ResponseEntity<Object> deleteEmp(@PathVariable(name = "id") Long empVoId) {
        EmpVo empVoFound = service.getEmpById(empVoId);
        if (empVoFound == null)
            return new ResponseEntity<>("employee doesn't exist", HttpStatus.NOT_FOUND);
        service.delete(empVoId);
        return new ResponseEntity<>("Employee is deleted successfully", HttpStatus.OK);
    }

    /**
     * Tri par champ : http://localhost:8080/rest/sort/name
     */
    @GetMapping(value = "/rest/sort/{fieldName}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public List<EmpVo> sortBy(@PathVariable String fieldName) {
        return service.sortBy(fieldName);
    }

    /**
     * Pagination : http://localhost:8080/rest/pagination/0/3
     */
    @GetMapping("/rest/pagination/{pageid}/{size}")
    public List<EmpVo> pagination(@PathVariable int pageid, @PathVariable int size) {
        return service.findAll(pageid, size);
    }
}