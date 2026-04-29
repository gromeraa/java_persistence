package br.com.fiap.games.dao;

import br.com.fiap.games.model.Game;
import jakarta.persistence.EntityManager;

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

}
