package aula.continuandocomspringnoturno.Controle;

import aula.continuandocomspringnoturno.Modelo.Dao.ReporterDAOClasse;
import aula.continuandocomspringnoturno.Modelo.entity.Reporter;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/reporter")
public class ReporterController {

    public ReporterController() throws SQLException {}

    @GetMapping("/form")
    public String form(Model model) {
        if (!model.containsAttribute("reporter")) {
            model.addAttribute("reporter", new Reporter());
        }
        return "reporter/form";
    }

    @PostMapping("/inserir")
    public String inserir(@Valid Reporter reporter, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws SQLException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao cadastrar. Verifique os campos.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/reporter/form";
        }

        ReporterDAOClasse dao = new ReporterDAOClasse();
        dao.inserir(reporter);

        redirectAttributes.addFlashAttribute("mensagem", "Repórter cadastrado com sucesso.");
        redirectAttributes.addFlashAttribute("tipoMensagem", "sucesso");

        return "redirect:/login";
    }

    @GetMapping("/listar")
    public String listar(Model model, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {
        Reporter reporterLogado = verificarUsuarioLogado(session, redirectAttributes);
        if (reporterLogado == null) {
            return "redirect:/login";
        }

        ReporterDAOClasse dao = new ReporterDAOClasse();
        List<Reporter> lista = dao.buscar();
        model.addAttribute("reporters", lista);
        return "reporter/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {
        Reporter reporterLogado = verificarUsuarioLogado(session, redirectAttributes);
        if (reporterLogado == null) {
            return "redirect:/login";
        }

        if (reporterLogado.getId() != id) {
            redirectAttributes.addFlashAttribute("mensagem", "Você só pode editar seu próprio usuário.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/reporter/listar";
        }

        ReporterDAOClasse dao = new ReporterDAOClasse();
        Reporter reporter = dao.buscar(id);

        if (reporter != null) {
            model.addAttribute("reporter", reporter);
            return "reporter/form";
        } else {
            return "redirect:/reporter/listar";
        }
    }

    @PostMapping("/atualizar")
    public String atualizar(@Valid Reporter reporter, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) throws SQLException {
        Reporter reporterLogado = verificarUsuarioLogado(session, redirectAttributes);
        if (reporterLogado == null) {
            return "redirect:/login";
        }

        if (reporter.getId() != reporterLogado.getId()) {
            redirectAttributes.addFlashAttribute("mensagem", "Você só pode atualizar seu próprio usuário.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/reporter/listar";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao atualizar. Verifique os campos.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/reporter/editar/" + reporter.getId();
        }

        ReporterDAOClasse dao = new ReporterDAOClasse();
        dao.editar(reporter);

        redirectAttributes.addFlashAttribute("mensagem", "Repórter atualizado com sucesso.");
        redirectAttributes.addFlashAttribute("tipoMensagem", "sucesso");

        return "redirect:/reporter/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") int id, RedirectAttributes redirectAttributes, HttpSession session) throws SQLException {
        Reporter reporterLogado = verificarUsuarioLogado(session, redirectAttributes);
        if (reporterLogado == null) {
            return "redirect:/login";
        }

        if (reporterLogado.getId() != id) {
            redirectAttributes.addFlashAttribute("mensagem", "Você só pode excluir seu próprio usuário.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/reporter/listar";
        }

        ReporterDAOClasse dao = new ReporterDAOClasse();
        dao.deletar(id);

        session.invalidate();

        redirectAttributes.addFlashAttribute("mensagem", "Usuário deletado com sucesso.");
        redirectAttributes.addFlashAttribute("tipoMensagem", "sucesso");

        return "redirect:/login";
    }

    private Reporter verificarUsuarioLogado(HttpSession session, RedirectAttributes redirectAttributes) {
        Reporter reporterLogado = (Reporter) session.getAttribute("usuarioLogado");

        if (reporterLogado == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Faça login para continuar.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
        }

        return reporterLogado;
    }
}



