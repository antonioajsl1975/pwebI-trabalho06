package aula.continuandocomspringnoturno.Controle;

import aula.continuandocomspringnoturno.Modelo.Dao.NoticiaDAOClasse;
import aula.continuandocomspringnoturno.Modelo.entity.Noticia;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final NoticiaDAOClasse noticiaDAO;

    public HomeController() throws SQLException {
        this.noticiaDAO = new NoticiaDAOClasse();
    }

    @GetMapping("/home")
    public String listarNoticiasHome(Model model) throws SQLException {
        List<Noticia> lista = noticiaDAO.listAll();
        model.addAttribute("noticias", lista);
        return "home";
    }
}

