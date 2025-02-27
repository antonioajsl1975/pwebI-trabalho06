package aula.continuandocomspringnoturno.Controle;

import aula.continuandocomspringnoturno.Modelo.Dao.ReporterDAOClasse;
import aula.continuandocomspringnoturno.Modelo.entity.Reporter;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/form")
    public String loginForm() {
        return "login/form";
    }

    @PostMapping("/autenticar")
    public String autenticar(String login, String senha, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {
        // Validação no Back-end
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
}
