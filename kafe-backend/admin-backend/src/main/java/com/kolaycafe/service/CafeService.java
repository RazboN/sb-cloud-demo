package com.kolaycafe.service;

import com.kolaycafe.model.Cafe;
import com.kolaycafe.model.NewCafeInform;
import com.kolaycafe.repository.ICafeRepository;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CafeService {
    @Autowired
    private ICafeRepository cafeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmailService emailService;

    public List<Cafe> getCafes() {
        return cafeRepository.findAll();
    }

    public Cafe createCafe(@NotNull Cafe newCafe) throws MessagingException {
        if(cafeRepository.findByName(newCafe.getName()).isPresent()){
            return null;
        }

        NewCafeInform newCafeInformations = modelMapper.map(newCafe, NewCafeInform.class);

        Cafe createdCafe = cafeRepository.save(newCafe);
        emailService.informNewCafe(newCafeInformations);

        return createdCafe;
    }

    public String deleteCafe(String id) {
        cafeRepository.deleteById(id);
        return id;
    }

    public Cafe getCafeById(String id) {
        return cafeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cafe not found."));
    }

    public Cafe updateCafe(@NonNull String id, @NonNull Cafe newCafe) {

        Cafe oldCafe = getCafeById(id);

        if (!oldCafe.getEmail().equals(newCafe.getEmail()))
        {
            NewCafeInform newCafeInformations = modelMapper.map(newCafe, NewCafeInform.class);
            emailService.informNewCafe(newCafeInformations);
            oldCafe.setEmail(newCafe.getEmail());
        }

        oldCafe.setName(newCafe.getName());
        oldCafe.setCreateDate(new Date());
        oldCafe.setCity(newCafe.getCity());
        oldCafe.setLicence(newCafe.isLicence());
        cafeRepository.save(oldCafe);

        return oldCafe;
    }
}
