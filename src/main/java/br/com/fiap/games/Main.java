package br.com.fiap.games;

import br.com.fiap.games.dao.GameDao;
import br.com.fiap.games.model.Game;
import br.com.fiap.games.utils.Conexao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManager em = Conexao.getEntityManager();

        //pesquisar(em);
        //cadastrar(em);
        //listarTodosOsGames(em);
        buscarGamesPorFaixaDeValores(em);
        em.close();

    }

    public static void listarTodosOsGames(EntityManager em) {
        GameDao dao = new GameDao(em);
        List<Game> lista = dao.listarTodosOsGames();

        for (Game game : lista) {
            System.out.println(game);
        }
    }

    public static void pesquisar(EntityManager em) {
        GameDao dao = new GameDao(em);
        Game game1 = new Game();
        game1.setId(2L);

        Game gameEncontrado = dao.buscaGamePeloId(game1);

        if (gameEncontrado != null) {
            System.out.println("Game encontrado");
            System.out.println(gameEncontrado);
        } else {
            System.out.println("Game não encontrado");
        }

    }

    public static void cadastrar(EntityManager em) {
        Game game1 = new Game();
        //game1.setId(6L);
        game1.setTitulo("FIFA 26");
        game1.setCategoria("Futebeol");
        game1.setDataLancamento(LocalDate.of(2025, 11, 13));
        game1.setFinalizado(false);
        game1.setProdutora("EA Sprots");
        game1.setValor(450.00);

        GameDao gameDao = new GameDao(em);
        em.getTransaction().begin();
        gameDao.salvar(game1);
        em.getTransaction().commit();
        em.close();
    }

    public static void buscarGamesPorFaixaDeValores(EntityManager em) {
        GameDao gameDao = new GameDao(em);
        List<Game> games = gameDao.buscarGamesPorFaixaDeValores(150.0, 300.0);
        for (Game game : games) {
            System.out.println(game);
            System.out.println("------------------------");
        }
    }
}
