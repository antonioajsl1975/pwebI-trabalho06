package aula.continuandocomspringnoturno.Modelo.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    private static Connection conexao;

    private FabricaConexao() {
    }

    public static Connection pegaConexao() throws SQLException{
        if (conexao == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/noticiasdb?useSSL=false",
                        "root",
                        "nova_senha"
                );
            } catch (ClassNotFoundException | SQLException e) {
                throw new SQLException(e);
            }
        }
        return conexao;
    }

    public static void fecharConexao() throws SQLException {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                conexao = null;
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
