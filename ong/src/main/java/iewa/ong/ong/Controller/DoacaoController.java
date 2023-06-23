package iewa.ong.ong.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import iewa.ong.ong.DAO.ConexaoDAO;
import iewa.ong.ong.DAO.DoacaoDAO;
import iewa.ong.ong.DTO.DoacaoDTO;

@Controller
@RequestMapping("/")
public class DoacaoController {
    Connection conn = null;

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String Home(Model model) throws SQLException {
        conn = new ConexaoDAO().Connect();
        DoacaoDAO repository = new DoacaoDAO();
        List<DoacaoDTO> doacoes = repository.ListarDoacaos(conn);
        model.addAttribute("listadoacoes", doacoes);
        return "home";
    }

    @RequestMapping("/novaDoacao")
    public String SignUp(Model model) {
        model.addAttribute("mnovaDoacao", "Nova Doação");
        return "novaDoacao";
    }

    // Requisições
    @RequestMapping(value = "/CriarDoacao", method = RequestMethod.POST)
    public String Create(Model modelo, String descricao, String nomeDoador, Float quantia, Integer quantidade)
        throws SQLException {
        conn = new ConexaoDAO().Connect();
        DoacaoDTO doacao = new DoacaoDTO();
        doacao.setDescricao(descricao);
        doacao.setNomeDoador(nomeDoador);
        doacao.setQuantia(quantia);
        doacao.setQuantidade(quantidade);
        DoacaoDAO obj = new DoacaoDAO();

        obj.CriarDoacao(doacao);
        obj.ListarDoacaos(conn);

        return "redirect:/home";
    }

    @PostMapping("/doacao/{id}")
    public String deletarDoacao(@PathVariable int id) throws SQLException {
        DoacaoDAO repository = new DoacaoDAO();
        repository.deletarDoacao(id);
        return "redirect:/home";
    }

    @PostMapping("/doacao/{id}/atualizar")
    public String atualizarDoacao(@PathVariable int id, @RequestParam("descricao") String descricao, @RequestParam("nomeDoador") String nomeDoador, @RequestParam("quantia") Float quantia,@RequestParam("quantidade") Integer quantidade) throws SQLException {
        DoacaoDAO repository = new DoacaoDAO();
        DoacaoDTO doacao = new DoacaoDTO();
        doacao.setDescricao(descricao);
        doacao.setNomeDoador(nomeDoador);
        doacao.setQuantia(quantia);
        doacao.setQuantidade(quantidade);

        repository.atualizarDoacao(doacao, id);
        return "redirect:/home";
    }

}
