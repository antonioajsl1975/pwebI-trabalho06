package aula.continuandocomspringnoturno.Controle;

import aula.continuandocomspringnoturno.Modelo.Dao.NoticiaDAOClasse;
import aula.continuandocomspringnoturno.Modelo.entity.Noticia;
import aula.continuandocomspringnoturno.Modelo.entity.Reporter;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/noticia")
public class NoticiaController {

    private final NoticiaDAOClasse noticiaDAO;

    public NoticiaController() throws SQLException {
        this.noticiaDAO = new NoticiaDAOClasse();
    }

    @GetMapping("/listar")
    public String listar(Model model, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {

        Reporter reporterLogado = verificarUsuarioLogado(session, redirectAttributes);
        if (reporterLogado == null) {
            return "redirect:/login";
        }

        List<Noticia> lista = noticiaDAO.listAll();
        model.addAttribute("noticias", lista);
        return "noticia/listar";
    }

    @GetMapping("/form")
    public String form(Model model, HttpSession session, RedirectAttributes redirectAttributes) throws SQLException {

        Reporter reporterLogado = verificarUsuarioLogado(session, redirectAttributes);
        if (reporterLogado == null) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("noticia")) {
            Noticia noticia = new Noticia();
            model.addAttribute("noticia", noticia);
        }
        return "noticia/form";
    }

    @PostMapping("/inserir")
    public String inserir(@Valid Noticia noticia, @RequestParam("imagemUpload") MultipartFile imagemUpload,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) throws SQLException {

        Reporter reporterLogado = (Reporter) session.getAttribute("usuarioLogado");

        if (reporterLogado == null) {
            return "redirect:/login/form";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao cadastrar notícia. Verifique os campos.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/noticia/form";
        }

        if (!imagemUpload.isEmpty()) {
            String nomeArquivo = System.currentTimeMillis() + "_" + imagemUpload.getOriginalFilename();
            String caminhoArquivo = "/caminho/para/pasta/imagens/" + nomeArquivo;

            try {
                imagemUpload.transferTo(new File(caminhoArquivo));
                noticia.setImagem(nomeArquivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        noticia.setReporter(reporterLogado);
        noticia.setData(new Timestamp(System.currentTimeMillis()));
        noticiaDAO.inserir(noticia);

        redirectAttributes.addFlashAttribute("mensagem", "Notícia cadastrada com sucesso.");
        redirectAttributes.addFlashAttribute("tipoMensagem", "sucesso");

        return "redirect:/noticia/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes, HttpSession session) throws SQLException {

        Reporter reporterLogado = verificarUsuarioLogado(session, redirectAttributes);
        if (reporterLogado == null) {
            return "redirect:/login";
        }

        Noticia noticia = noticiaDAO.buscarPorId(id);

        if (noticia == null || noticia.getReporter().getId() != reporterLogado.getId()) {
            redirectAttributes.addFlashAttribute("mensagem", "Você não tem permissão para editar esta notícia.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/noticia/listar";
        }

        model.addAttribute("noticia", noticia);
        return "noticia/form";
    }

    @PostMapping("/atualizar")
    public String atualizar(@Valid Noticia noticia, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession session) throws SQLException {

        Reporter reporterLogado = verificarUsuarioLogado(session, redirectAttributes);
        if (reporterLogado == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao atualizar notícia. Verifique os campos.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/noticia/editar/" + noticia.getId();
        }

        Noticia noticiaExistente = noticiaDAO.buscarPorId(noticia.getId());

        if (noticiaExistente == null || noticiaExistente.getReporter().getId() != reporterLogado.getId()) {
            redirectAttributes.addFlashAttribute("mensagem", "Você não tem permissão para atualizar esta notícia.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/noticia/listar";
        }

        noticia.setReporter(reporterLogado);

        noticia.setData(noticiaExistente.getData());

        noticiaDAO.atualizar(noticia);

        redirectAttributes.addFlashAttribute("mensagem", "Notícia atualizada com sucesso.");
        redirectAttributes.addFlashAttribute("tipoMensagem", "sucesso");

        return "redirect:/noticia/listar";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") int id, RedirectAttributes redirectAttributes, HttpSession session) throws SQLException {

        Reporter reporterLogado = verificarUsuarioLogado(session, redirectAttributes);
        if (reporterLogado == null) {
            return "redirect:/login";
        }

        Noticia noticia = noticiaDAO.buscarPorId(id);

        if (noticia == null || noticia.getReporter().getId() != reporterLogado.getId()) {
            redirectAttributes.addFlashAttribute("mensagem", "Você não tem permissão para deletar esta notícia.");
            redirectAttributes.addFlashAttribute("tipoMensagem", "erro");
            return "redirect:/noticia/listar";
        }

        noticiaDAO.deletar(id);

        redirectAttributes.addFlashAttribute("mensagem", "Notícia deletada com sucesso.");
        redirectAttributes.addFlashAttribute("tipoMensagem", "sucesso");

        return "redirect:/noticia/listar";
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
