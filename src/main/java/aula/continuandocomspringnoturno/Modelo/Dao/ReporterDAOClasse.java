package aula.continuandocomspringnoturno.Modelo.Dao;

import aula.continuandocomspringnoturno.Modelo.entity.Reporter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReporterDAOClasse implements ReporterDaoInterface{

    Connection conexao;

    public ReporterDAOClasse() throws SQLException {
        conexao = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Reporter reporter) throws SQLException {
        String sql = "INSERT INTO reporter (nome, login, senha) VALUES (?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, reporter.getNome());
        pstm.setString(2, reporter.getLogin());
        pstm.setString(3, reporter.getSenha());
        pstm.executeUpdate();
    }

    @Override
    public void editar(Reporter reporter) throws SQLException {
        if (reporter.getId() == 0) {
            throw new SQLException("ID do repórter não encontrado para atualização.");
        }

        String sql = "UPDATE reporter SET nome = ?, login = ?, senha = ? WHERE id = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, reporter.getNome());
        pstm.setString(2, reporter.getLogin());
        pstm.setString(3, reporter.getSenha());
        pstm.setInt(4, reporter.getId());

        int linhasAfetadas = pstm.executeUpdate();
        if (linhasAfetadas == 0) {
            throw new SQLException("Nenhum registro foi atualizado. Verifique se o ID é válido.");
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM reporter WHERE id = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, id);
        pstm.executeUpdate();
    }

    @Override
    public Reporter buscar(int id) throws SQLException {
        String sql = "SELECT * FROM reporter WHERE id = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return new Reporter(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("senha")
            );
        }
        return null;
    }

    @Override
    public List<Reporter> buscar() throws SQLException {
        List<Reporter> lista = new ArrayList<>();
        String sql = "SELECT * FROM reporter";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            lista.add(new Reporter(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("senha")
            ));
        }
        return lista;
    }

    @Override
    public void sair() throws SQLException {
        FabricaConexao.fecharConexao();
    }

    @Override
    public Reporter autenticar(String login, String senha) throws SQLException {
        String sql = "SELECT * FROM reporter WHERE login = ? AND senha = ? LIMIT 1";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, login);
        pstm.setString(2, senha);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return new Reporter(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("login"),
                    rs.getString("senha")
            );
        }
        return null;
    }
}