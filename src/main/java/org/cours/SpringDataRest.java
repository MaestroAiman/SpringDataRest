package org.cours;

import org.cours.modele.Proprietaire;
import org.cours.modele.ProprietaireRepo;
import org.cours.modele.Voiture;
import org.cours.modele.VoitureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDataRest {

    @Autowired
    private VoitureRepo repository;

    @Autowired
    private ProprietaireRepo proprietaireRepo;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRest.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            if (repository.count() == 0) {
                Proprietaire p1 = new Proprietaire("Ali",   "Hassan");
                Proprietaire p2 = new Proprietaire("Najat", "Bani");
                proprietaireRepo.save(p1);
                proprietaireRepo.save(p2);

                Voiture v1 = new Voiture("Toyota", "Corolla", "Grise", "A-1-9090", 2018, 95000);
                v1.setProprietaire(p1);
                repository.save(v1);

                Voiture v2 = new Voiture("Ford", "Fiesta", "Rouge", "A-2-8090", 2015, 90000);
                v2.setProprietaire(p1);
                repository.save(v2);

                Voiture v3 = new Voiture("Honda", "CRV", "Bleu", "A-3-7090", 2016, 140000);
                v3.setProprietaire(p2);
                repository.save(v3);
            }
        };
    }
}
