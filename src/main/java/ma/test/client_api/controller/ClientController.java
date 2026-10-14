package ma.test.client_api.controller;

import ma.test.client_api.entity.Agence;
import ma.test.client_api.entity.Client;
import ma.test.client_api.repository.AgenceRepository;
import ma.test.client_api.repository.ClientRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientRepository clientRepository;
    private final AgenceRepository agenceRepository;

    public ClientController(
            ClientRepository clientRepository,
            AgenceRepository agenceRepository) {
        this.clientRepository = clientRepository;
        this.agenceRepository = agenceRepository;
    }

    // GET: afficher tous les clients
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // GET: afficher un client par ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Client introuvable"
                ));
    }

    // POST: ajouter un client
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@Valid @RequestBody Client client) {

        // Vérifier si le CIN existe déjà
        if (clientRepository.existsByCin(client.getCin())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ce CIN existe deja"
            );
        }

        // Agence facultative : vérifier uniquement si elle est fournie
        if (client.getAgence() != null
                && client.getAgence().getId() != null) {

            Agence agence = agenceRepository.findById(
                    client.getAgence().getId()
            ).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Agence introuvable"
            ));

            client.setAgence(agence);
        } else {
            client.setAgence(null);
        }

        // Statut facultatif : ACTIF par défaut
        if (client.getStatut() == null
                || client.getStatut().isBlank()) {
            client.setStatut("ACTIF");
        }

        return clientRepository.save(client);
    }

    // PUT: modifier un client
    @PutMapping("/{id}")
    public Client updateClient(
            @PathVariable Long id,
            @Valid @RequestBody Client client) {

        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Client introuvable"
                ));

        // Vérifier l'unicité du CIN
        if (!existingClient.getCin().equals(client.getCin())
                && clientRepository.existsByCin(client.getCin())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ce CIN existe deja"
            );
        }

        // Mettre à jour les champs obligatoires
        existingClient.setCin(client.getCin());
        existingClient.setNom(client.getNom());
        existingClient.setPrenom(client.getPrenom());
        existingClient.setTelephone(client.getTelephone());

        // Mettre à jour les champs facultatifs
        existingClient.setDateNaissance(client.getDateNaissance());
        existingClient.setEmail(client.getEmail());
        existingClient.setAdresse(client.getAdresse());
        existingClient.setVille(client.getVille());
        existingClient.setCodePostal(client.getCodePostal());
        existingClient.setProfession(client.getProfession());

        // Agence facultative
        if (client.getAgence() != null
                && client.getAgence().getId() != null) {

            Agence agence = agenceRepository.findById(
                    client.getAgence().getId()
            ).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Agence introuvable"
            ));

            existingClient.setAgence(agence);
        } else {
            existingClient.setAgence(null);
        }

        // Statut facultatif : garder l'ancien s'il n'est pas fourni
        if (client.getStatut() != null
                && !client.getStatut().isBlank()) {
            existingClient.setStatut(client.getStatut());
        }

        return clientRepository.save(existingClient);
    }

    // DELETE: supprimer un client
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {

        if (!clientRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Client introuvable"
            );
        }

        clientRepository.deleteById(id);
    }
}