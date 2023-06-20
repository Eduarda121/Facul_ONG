package iewa.ong.ong.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import iewa.ong.ong.DAO.ConexaoDAO;
import iewa.ong.ong.DAO.UsuarioDAO;
import iewa.ong.ong.DTO.UsuarioDTO;

@Controller
@RequestMapping("/")
public class UsuarioController {

    //TELAS
    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String Home(Model model) throws SQLException {
        UsuarioDAO repository = new UsuarioDAO();
        List<UsuarioDTO> usuarios = repository.ListarUsuarios();
        model.addAttribute("listausuarios", usuarios);
        return "home";
    }

    @RequestMapping("/novoUsuario")
    public String SignUp(Model model) {
        model.addAttribute("mnovoUsuario", "Novo Usuário");
        return "novoUsuario";
    }

    //Requisições
    @RequestMapping(value = "/CriarUsuario", method = RequestMethod.POST)
    public String Create(Model modelo, String nome, String email, String senha) throws SQLException {

        UsuarioDTO user = new UsuarioDTO();
        user.setNome(nome);
        user.setEmail(email);
        user.setSenha(senha);

        modelo.addAttribute(
                "fcriarUsuario",
                "Nome: " +
                        nome +
                        "\nEmail: " +
                        email +
                        "\n Idade: " +
                        senha);

        UsuarioDAO obj = new UsuarioDAO();

        obj.CriarUsuario(user);
        obj.ListarUsuarios();

        return "home";
    }

}
