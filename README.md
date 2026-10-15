# Application de gestion des clients

## Présentation

Application web de gestion des clients développée dans le cadre d’un test technique. Elle permet de consulter, rechercher, ajouter et modifier des clients selon les autorisations de l’utilisateur.

## Technologies

- Frontend : Angular, TypeScript, HTML, CSS
- Backend : Java 21, Spring Boot, Spring Security
- Base de données : PostgreSQL
- Authentification : Keycloak, OpenID Connect, JWT
- Outils : Git, GitHub, Maven, npm

## Dépôts GitHub

- Backend : https://github.com/IlhamKaddi/client-management-test-
- Frontend : https://github.com/IlhamKaddi/client-management-frontend

## Rôles

- **LECTEUR** : consulter, rechercher et afficher les détails des clients.
- **GESTIONNAIRE** : consulter, rechercher, ajouter et modifier les clients.

Les autorisations sont vérifiées côté backend. L’interface adapte également les actions visibles au rôle connecté.

## Fonctionnalités

- Connexion et déconnexion via Keycloak
- Liste des clients avec pagination
- Recherche par CIN, nom/prénom et agence
- Consultation et modification d’un client
- Ajout de client selon le rôle
- Validation des champs et contrôle de l’unicité de la CIN
- Messages de validation, de succès et d’erreur

## Prérequis

- Java JDK 21
- Node.js et npm
- PostgreSQL
- Keycloak

## Lancement du backend

1. Créer la base PostgreSQL `clients_management`.
2. Configurer la connexion à la base de données dans la configuration du backend.
3. Démarrer Keycloak sur le port `8180` et vérifier la configuration du realm `client-management`.
4. Ouvrir un terminal dans `client-api/client-api`.

Sous PowerShell :

```powershell
$env:DB_PASSWORD="VOTRE_MOT_DE_PASSE"
.\mvnw.cmd spring-boot:run
```

API : http://localhost:8080

## Lancement du frontend

Ouvrir un terminal dans `client-frontend` :

```bash
npm install
npm start
```

Frontend : http://localhost:4200

Keycloak : http://localhost:8180

## Tests des rôles

**LECTEUR**
- Se connecter avec le compte de test LECTEUR.
- Consulter, rechercher et afficher les détails des clients.
- Vérifier que les actions d’ajout et de modification ne sont pas disponibles.

**GESTIONNAIRE**
- Se connecter avec le compte de test GESTIONNAIRE.
- Ajouter et modifier un client.
- Vérifier les validations et les messages affichés.

## Configuration et sécurité

Les identifiants locaux et secrets ne doivent pas être publiés dans Git. Configurer les variables d’environnement et les comptes de test dans l’environnement local.

## Livrables

- Code source du backend et du frontend via les dépôts GitHub ci-dessus.
- Script SQL de création de la base de données : à ajouter au dépôt.
- Export du realm Keycloak sans secrets : à ajouter au dépôt.
