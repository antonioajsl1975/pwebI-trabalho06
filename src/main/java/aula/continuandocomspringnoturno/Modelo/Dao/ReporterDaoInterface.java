package aula.continuandocomspringnoturno.Modelo.Dao;

import aula.continuandocomspringnoturno.Modelo.entity.Reporter;

import java.sql.SQLException;
import java.util.List;

public interface ReporterDaoInterface {
    void inserir(Reporter reporter) throws SQLException;
    void deletar(int id) throws SQLException;
    Reporter buscar(int id) throws SQLException;
    List<Reporter> buscar() throws SQLException;
    void editar(Reporter reporter) throws SQLException;
    void sair() throws SQLException;
    Reporter autenticar(String login, String senha) throws SQLException;
}


