package aula.continuandocomspringnoturno.Controle;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import aula.continuandocomspringnoturno.Modelo.Dao.ReporterDAOClasse;
import aula.continuandocomspringnoturno.Modelo.entity.Reporter;

import java.sql.SQLException;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "login/form";
    }

    @PostMapping("/autenticar")
    public String autenticar(String login, String senha, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {
        if (login == null || login.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagem", "Login e senha são obrigatórios.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/login";
        }

        ReporterDAOClasse dao = new ReporterDAOClasse();
        Reporter reporter = dao.autenticar(login, senha);

        if (reporter != null) {
            session.setAttribute("usuarioLogado", reporter);
            return "redirect:/reporter/listar";
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Login ou senha inválidos.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate(); // Invalida a sessão do usuário
        redirectAttributes.addFlashAttribute("mensagem", "Logout realizado com sucesso.");
        redirectAttributes.addFlashAttribute("tipoMensagem", "sucesso");
        return "redirect:/login"; // Redireciona para a tela de login
    }
}
