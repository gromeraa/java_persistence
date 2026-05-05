package br.com.fiap.games.dao;

import br.com.fiap.games.model.Game;
import jakarta.persistence.EntityManager;

import java.util.List;

public class GameDao {

    private EntityManager em;

    public GameDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(Game game) {
        em.persist(game);
    }

    public void atualizar(Game game) {
        em.merge(game);
    }

    public void excluir(Game game) {
        Game gameExcluir = em.find(Game.class, game.getId());
        em.remove(gameExcluir);
    }

    public Game buscaGamePeloId(Game game) {
        return em.find(Game.class, game.getId());
    }

    public List<Game> listarTodosOsGames() {
        String jpqlQuery = "SELECT g FROM Game g ORDER BY g.titulo ASC";
        return em.createQuery(jpqlQuery, Game.class).getResultList();
    }

    public List<Game> buscarGamesPorFaixaDeValores(Double valorIncial, Double ValorFinal) {
        String jpqlQuery = "SELECT g FROM Game g WHERE g.valor BETWEEN :valorInicial AND :valorFinal ORDER BY g.titulo ASC ";
        return em.createQuery(jpqlQuery, Game.class)
                .setParameter("valorInicial", valorIncial)
                .setParameter("valorFinal", ValorFinal)
                .getResultList();
    }

}