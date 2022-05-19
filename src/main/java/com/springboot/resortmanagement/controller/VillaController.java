package com.springboot.resortmanagement.controller;

import com.springboot.resortmanagement.dto.VillaDto;
import com.springboot.resortmanagement.entity.Resort;
import com.springboot.resortmanagement.entity.Villa;
import com.springboot.resortmanagement.exception.ResortNotFoundException;
import com.springboot.resortmanagement.service.ResortService;
import com.springboot.resortmanagement.service.VillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// to get db data in JSON format
//@RestController

@Controller
@RequestMapping("/villas")
public class VillaController {

    @Autowired
    private VillaService villaService;
    @Autowired
    public ResortService resortService;

    String villaForm= "villas/villa-form";

    String villaShow = "villas/showVillas";

    String resortIdno = "resortId";

    @GetMapping("showAll")
    public String findAll(Model theModel){
        List<Villa> villas= villaService.findAll();
        theModel.addAttribute("villas",villas);
        return villaShow;
    }

    @GetMapping("/showFormForAdd/{Id}")
    public String showFormForAdd(Model theModel,@PathVariable int id){
        Villa theVilla= new Villa();
        theModel.addAttribute("villa",theVilla);
        theModel.addAttribute(resortIdno,id);
        return villaForm;
    }

    @PostMapping("/save/{Id}")
    public String saveVilla(@Valid @ModelAttribute("villa") Villa theVilla, BindingResult result, @PathVariable int Id){
        if(result.hasErrors()){
            return villaForm;
        }else {
            Resort theResort = resortService.findById(Id);
            theVilla.setResort(theResort);
            villaService.save(theVilla);
            return "redirect:/villas/findVillas?resortId=" + Id;
        }
    }

    @GetMapping("/showFormForUpdate")
    public String updateVilla(@RequestParam("villaId") int theId, @RequestParam("resortId") int id , Model theModel){
        Villa theVilla = villaService.findById(theId);
        theModel.addAttribute(theVilla);
        theModel.addAttribute(resortIdno, id);
        return villaForm;
    }

    @GetMapping("/deleteById")
    public String deleteVilla(@RequestParam("villaId") int theId,@RequestParam("resortId") int id){
        villaService.deleteById(theId);
        return "redirect:/villas/findVillas?resortId="+id;
    }

    @GetMapping("/findVillas")
    public String findVillas(@RequestParam("resortId") int theId, Model theModel){
        List<Villa> villas=villaService.findVillas(theId);
        Resort resorts = resortService.findById(theId);
        if(resorts!=null) {
            theModel.addAttribute("resortVillas", villas);
            theModel.addAttribute(resortIdno, theId);
            return villaShow;
        }else{
            throw new ResortNotFoundException("Resort id not found - " + theId);
        }

    }


    @GetMapping("/viewVillas")
    public String viewVillas(@RequestParam("resortId") int theId, Model theModel){
        List<Villa> villas=villaService.findVillas(theId);
        if(villas.isEmpty()){
            throw new ResortNotFoundException("Resort id not found - " + theId);
        }
        theModel.addAttribute("resortVillas",villas);
        theModel.addAttribute(resortIdno,theId);
        return villaShow;
    }

    //for dto testing
    @GetMapping("/villa-list")
    public List<VillaDto> getAllVillas(){
        return villaService.getAllVillas();
    }
}
