package aula.continuandocomspringnoturno.Modelo.Dao;

import aula.continuandocomspringnoturno.Modelo.entity.Noticia;

import java.sql.SQLException;
import java.util.List;

public interface NoticiaDAOInterface {
    void inserir(Noticia noticia) throws SQLException;
    List<Noticia> listarPorReporter(int idReporter) throws SQLException;
    Noticia buscarPorId(int id) throws SQLException;
    void atualizar(Noticia noticia) throws SQLException;
    void deletar(int id) throws SQLException;
    List<Noticia> listAll() throws SQLException;
}
