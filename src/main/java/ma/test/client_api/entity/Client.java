package ma.test.client_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le CIN est obligatoire")
    @Size(
        max = 20,
        message = "Le CIN ne doit pas depasser 20 caracteres"
    )
    @Column(nullable = false, unique = true, length = 20)
    private String cin;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false, length = 100)
    private String nom;

    @NotBlank(message = "Le prenom est obligatoire")
    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @NotBlank(message = "Le telephone est obligatoire")
    @Column(nullable = false, length = 20)
    private String telephone;

    @Email(message = "L'adresse email est invalide")
    @Column(length = 150)
    private String email;

    @Column(length = 255)
    private String adresse;

    @Column(length = 100)
    private String ville;

    @Column(name = "code_postal", length = 10)
    private String codePostal;

    @Column(length = 100)
    private String profession;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "agence_id", nullable = true)
    private Agence agence;

    @Column(nullable = false, length = 20)
    private String statut = "ACTIF";

    @Column(
        name = "created_at",
        insertable = false,
        updatable = false
    )
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    public Client() {
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}