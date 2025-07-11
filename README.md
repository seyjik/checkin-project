Utilisation
Endpoints disponibles
1. Récupérer toutes les réservations
bash
GET http://localhost:8080/api/reservations
2. Récupérer une réservation spécifique
bash
GET http://localhost:8080/api/reservations/1
3. Effectuer un check-in
bash
POST http://localhost:8080/api/checkin
Content-Type: application/json

{
  "numeroIdentite": "jean.dupont@email.com"
}
