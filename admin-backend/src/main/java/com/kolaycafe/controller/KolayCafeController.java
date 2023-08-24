package com.kolaycafe.controller;

import com.kolaycafe.dto.CafeDTO;
import com.kolaycafe.exceptions.EntityExceptions;
import com.kolaycafe.model.Cafe;
import com.kolaycafe.repository.ICafeRepository;
import com.kolaycafe.service.CafeService;
import com.kolaycafe.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/admin/cafe")
public class KolayCafeController {

    @Autowired
    private CafeService cafeService;

    @Autowired
    private ICafeRepository iCafeRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/all")
    public ResponseEntity<List<Cafe>> getCafes(){
        try {
            List<Cafe> cafes = cafeService.getCafes();

            // Kayıt yoksa notfound dönecek.
            if (cafes.isEmpty()) {
                return new ResponseEntity<>(NOT_FOUND);
            }
            return new ResponseEntity<>(cafes, OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCafe(@PathVariable String id){
        try{
            String successMesage = "Görüntülenen cafe Id: " + getCafeById(id);
            return new ResponseEntity<>(successMesage, OK);
        }
        catch (Exception ex)
        {
            String errorMesage = "Hata Mesajı" + ex.getMessage();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorMesage);
        }
    }

    @PostMapping
    public ResponseEntity<String> careateCafe(@RequestBody @Valid Cafe newCafe) throws Exception {
        Cafe createdCafe = cafeService.createCafe(newCafe);

        if (createdCafe == null) {
            throw new EntityExceptions("Already added!");
        }

        String successMesage = "Added: "+ createdCafe;

        return new ResponseEntity<>(successMesage,OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateCafe(@PathVariable String id, @RequestBody @Valid CafeDTO newCafe){
        try {
            cafeService.updateCafe(id,newCafe);
            String successMesage = "Updated: "+ newCafe;
            return new ResponseEntity<>(successMesage,OK);
        }
        catch (Exception ex){
            String errorMesage = "Error" + ex.getMessage();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorMesage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCafe(@PathVariable String id){
        try{
            cafeService.deleteCafe(id);
            String successMesage = "Deleted: " + id;
            return new ResponseEntity<>(successMesage, OK);
        }
        catch (Exception ex){
            String errorMesage = "Error " + ex.getMessage();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorMesage);
        }
    }

    private Cafe getCafeById(String id){
        return cafeService.getCafeById(id);
    }

}