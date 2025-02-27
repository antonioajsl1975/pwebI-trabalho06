package aula.continuandocomspringnoturno.Modelo.Dao;

import aula.continuandocomspringnoturno.Modelo.entity.Noticia;
import aula.continuandocomspringnoturno.Modelo.entity.Reporter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoticiaDAOClasse implements NoticiaDAOInterface {

    Connection conexao;

    public NoticiaDAOClasse() throws SQLException {
        conexao = FabricaConexao.pegaConexao();
    }

    @Override
    public void inserir(Noticia noticia) throws SQLException {
        String sql = "INSERT INTO noticia (titulo, lide, corpo, reporter_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getLide());
            stmt.setString(3, noticia.getCorpo());
            stmt.setInt(4, noticia.getReporter().getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Noticia> listarPorReporter(int idReporter) throws SQLException {
        List<Noticia> noticias = new ArrayList<>();
        String sql = "SELECT * FROM noticia WHERE reporter_id = ? ORDER BY data DESC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idReporter);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Noticia noticia = new Noticia();
                noticia.setId(rs.getInt("id"));
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setLide(rs.getString("lide"));
                noticia.setCorpo(rs.getString("corpo"));
                noticia.setData(rs.getTimestamp("data"));

                noticias.add(noticia);
            }
        }
        return noticias;
    }

    @Override
    public Noticia buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM noticia WHERE id = ?";
        Noticia noticia = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                noticia = new Noticia();
                noticia.setId(rs.getInt("id"));
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setLide(rs.getString("lide"));
                noticia.setCorpo(rs.getString("corpo"));
                noticia.setData(rs.getTimestamp("data"));
            }
        }
        return noticia;
    }

    @Override
    public List<Noticia> listAll() throws SQLException {
        List<Noticia> lista = new ArrayList<>();
        String sql = "SELECT n.id, n.titulo, n.lide, n.corpo, n.data, r.id AS reporter_id, r.nome AS nome_reporter " +
                "FROM noticia n INNER JOIN reporter r ON n.reporter_id = r.id " +
                "ORDER BY n.data DESC";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Noticia noticia = new Noticia();
                noticia.setId(rs.getInt("id"));
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setLide(rs.getString("lide"));
                noticia.setCorpo(rs.getString("corpo"));
                noticia.setData(rs.getTimestamp("data"));

                Reporter reporter = new Reporter();
                reporter.setId(rs.getInt("reporter_id"));
                reporter.setNome(rs.getString("nome_reporter"));

                noticia.setReporter(reporter);
                lista.add(noticia);
            }
        }
        return lista;
    }

    @Override
    public void atualizar(Noticia noticia) throws SQLException {
        String sql = "UPDATE noticia SET titulo = ?, lide = ?, corpo = ? WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, noticia.getTitulo());
            stmt.setString(2, noticia.getLide());
            stmt.setString(3, noticia.getCorpo());
            stmt.setInt(4, noticia.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM noticia WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}