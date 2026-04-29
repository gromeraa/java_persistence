package br.com.fiap.games;

import br.com.fiap.games.dao.GameDao;
import br.com.fiap.games.model.Game;
import br.com.fiap.games.utils.Conexao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Game game1 = new Game();
        game1.setId(3L); // ID só é necessário quando já possuo o objeto criado no banco de dados
        game1.setTitulo("FIFA");
        game1.setCategoria("Futebeol");
        game1.setDataLancamento(LocalDate.of(2026, 5, 13));
        game1.setFinalizado(false);
        game1.setProdutora("EA Sprots");
        game1.setValor(450.00);


        EntityManager em = Conexao.getEntityManager();
        GameDao gameDao = new GameDao(em);

        em.getTransaction().begin();
        //gameDao.salvar(game1);
        //gameDao.atualizar(game1);
        gameDao.excluir(game1);
        em.getTransaction().commit();
        em.close();

    }

}
