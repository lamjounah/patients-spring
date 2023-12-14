package com.example.patient.web;

import com.example.patient.entite.Patient;
import com.example.patient.repositorises.PatientRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientControler {
    private PatientRepository patientRepository;
    @GetMapping(path = "/index")
    public  String patiens(Model model,
                           @RequestParam(name = "page",defaultValue = "0")int page,
                           @RequestParam(name = "size",defaultValue = "5")int size,
                           @RequestParam(name = "keyword",defaultValue = "")String keyword
                          ){

        Page<Patient> pagePatinets = patientRepository.findByNomContains(keyword,PageRequest.of(page,size));
        model.addAttribute("listpatiens",pagePatinets.getContent());
        model.addAttribute("pages",new int[pagePatinets.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patiens";
    }
    @GetMapping("/delete")
    public  String delete(Long id ,String keyword ,int page){
        patientRepository.deleteById(id);
        return  "redirect:index?page="+page+"&keyword="+keyword;

    }
    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
    @GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @PostMapping("/save")
    public  String save(Model model , @Valid Patient patient, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/formPatients";

    }
    @PostMapping(path = "/update")
    public  String update(Model model , @Valid Patient patient, BindingResult bindingResult,
                          @RequestParam(defaultValue= "0") int page,
                          @RequestParam(defaultValue = "")String keyword){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;

    }
    @GetMapping("/editPatient")
    public String editPatient(Model model,Long id,int page,String  keyword){
        Patient patient =patientRepository.findById(id).orElse(null);
        if (patient==null)throw new RuntimeException("Patients introvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }
}
