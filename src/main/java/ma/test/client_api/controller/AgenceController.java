
package ma.test.client_api.controller;

import ma.test.client_api.entity.Agence;
import ma.test.client_api.repository.AgenceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final AgenceRepository agenceRepository;

    public AgenceController(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @GetMapping
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }
}
