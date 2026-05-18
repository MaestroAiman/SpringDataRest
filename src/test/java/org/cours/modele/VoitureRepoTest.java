package org.cours.modele;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class VoitureRepoTest {

    @Autowired
    private VoitureRepo voitureRepo;

    @Autowired
    private ProprietaireRepo proprietaireRepo;

    private Proprietaire savedProprietaire() {
        return proprietaireRepo.save(new Proprietaire("Test", "User"));
    }

    @Test
    void ajouterVoiture() {
        Proprietaire p = savedProprietaire();
        Voiture v = new Voiture("Toyota", "Corolla", "Grise", "AB-123-CD", 2020, 80000);
        v.setProprietaire(p);

        Voiture saved = voitureRepo.save(v);

        assertThat(saved.getId()).isGreaterThan(0L);
    }

    @Test
    void supprimerVoiture() {
        Proprietaire p = savedProprietaire();

        Voiture f = new Voiture("Ford",  "Fiesta", "Rouge", "XX-001-YY", 2015, 60000);
        f.setProprietaire(p);
        voitureRepo.save(f);

        Voiture h = new Voiture("Honda", "CRV",   "Bleu",  "XX-002-YY", 2016, 90000);
        h.setProprietaire(p);
        voitureRepo.save(h);

        voitureRepo.deleteAll();

        assertThat((List<Voiture>) voitureRepo.findAll()).isEmpty();
    }
}
