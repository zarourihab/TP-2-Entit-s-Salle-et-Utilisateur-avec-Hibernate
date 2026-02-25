package com.example.model;

import javax.persistence.*;

@Entity
public class Salle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private int capacite;
    private String description;
    private int etage;
    private boolean disponible = true;

    public Salle() {
    }

    public Salle(String nom, int capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

    public Long getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getEtage() { return etage; }
    public void setEtage(int etage) { this.etage = etage; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public String toString() {
        return "Salle{id=" + id +
                ", nom='" + nom + '\'' +
                ", capacite=" + capacite +
                ", disponible=" + disponible +
                '}';
    }
}