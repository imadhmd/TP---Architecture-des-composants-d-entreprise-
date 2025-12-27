package ma.formations.springmvcrestdatajpa.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ma.formations.springmvcrestdatajpa.domaine.EmpVo;
import ma.formations.springmvcrestdatajpa.service.IService;

@Controller
public class EmpController {

    @Autowired
    private IService service;

    /**
     * Page d'accueil : http://localhost:8080
     */
    @RequestMapping("/")
    public String showWelcomeFile(Model m) {
        return "index";
    }

    /**
     * Afficher le formulaire d'ajout : http://localhost:8080/empform
     */
    @RequestMapping("/empform")
    public String showform(Model m) {
        m.addAttribute("empVo", new EmpVo());
        return "empform";
    }

    /**
     * Sauvegarder un nouvel employé
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("empVo") EmpVo emp) {
        service.save(emp);
        return "redirect:/viewemp";
    }

    /**
     * Afficher la liste des employés : http://localhost:8080/viewemp
     */
    @RequestMapping("/viewemp")
    public String viewemp(Model m) {
        List<EmpVo> list = service.getEmployees();
        m.addAttribute("list", list);
        return "viewemp";
    }

    /**
     * Afficher le formulaire de modification : http://localhost:8080/editemp/1
     */
    @RequestMapping(value = "/editemp/{id}")
    public String edit(@PathVariable Long id, Model m) {
        EmpVo emp = service.getEmpById(id);
        m.addAttribute("empVo", emp);
        return "empeditform";
    }

    /**
     * Sauvegarder les modifications
     */
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("empVo") EmpVo emp) {
        service.save(emp);
        return "redirect:/viewemp";
    }

    /**
     * Supprimer un employé : http://localhost:8080/deleteemp/1
     */
    @RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/viewemp";
    }

    /**
     * Chercher par salaire : http://localhost:8080/salary/8500
     */
    @RequestMapping("/salary/{salary}")
    public String getBySalary(@PathVariable Double salary, Model m) {
        List<EmpVo> list = service.findBySalary(salary);
        m.addAttribute("list", list);
        return "viewemp";
    }

    /**
     * Chercher par fonction : http://localhost:8080/fonction/Technicien
     */
    @RequestMapping("/fonction/{fonction}")
    public String getByFonction(@PathVariable String fonction, Model m) {
        List<EmpVo> list = service.findByFonction(fonction);
        m.addAttribute("list", list);
        return "viewemp";
    }

    /**
     * Chercher par salaire ET fonction
     */
    @RequestMapping("/salary_and_fonction/{salary}/{fonction}")
    public String getBySalaryAndFonction(@PathVariable Double salary,
                                         @PathVariable String fonction, Model m) {
        List<EmpVo> list = service.findBySalaryAndFonction(salary, fonction);
        m.addAttribute("list", list);
        return "viewemp";
    }

    /**
     * Employé avec le salaire maximum
     */
    @RequestMapping("/max_salary")
    public String getMaxSalary(Model m) {
        EmpVo empVo = service.getEmpHavaingMaxSalary();
        List<EmpVo> list = new ArrayList<>();
        list.add(empVo);
        m.addAttribute("list", list);
        return "viewemp";
    }

    /**
     * Pagination : http://localhost:8080/pagination/0/3
     * (page 0, 3 éléments par page)
     */
    @RequestMapping("/pagination/{pageid}/{size}")
    public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
        List<EmpVo> list = service.findAll(pageid, size);
        m.addAttribute("list", list);
        return "viewemp";
    }

    /**
     * Tri par champ : http://localhost:8080/sort/name
     */
    @RequestMapping("/sort/{fieldName}")
    public String sortBy(@PathVariable String fieldName, Model m) {
        List<EmpVo> list = service.sortBy(fieldName);
        m.addAttribute("list", list);
        return "viewemp";
    }
}