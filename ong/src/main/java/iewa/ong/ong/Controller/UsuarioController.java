package iewa.ong.ong.Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import iewa.ong.ong.DAO.ConexaoDAO;
import iewa.ong.ong.DAO.UsuarioDAO;
import iewa.ong.ong.DTO.UsuarioDTO;

@Controller
@RequestMapping("/")
public class UsuarioController {
    Connection conn = null;
    UsuarioDAO repository = new UsuarioDAO();
    
    // @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    // public String Home(Model model) throws SQLException {
    //     conn = new ConexaoDAO().Connect();
    //     UsuarioDAO repository = new UsuarioDAO();
    //     List<UsuarioDTO> usuarios = repository.ListarUsuarios(conn);
    //     model.addAttribute("listausuarios", usuarios);
    //     return "home";
    // }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("senha") String senha, Model model) {
        try {
            if (repository.fazerLogin(email, senha)) {
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Credenciais inválidas");
                return "/";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            model.addAttribute("error", "Erro ao fazer login");
            return "/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Ocorreu um erro. Tente novamente.");
            return "/";
        }
    }

    @RequestMapping("/novoUsuario")
    public String SignUp(Model model) {
        model.addAttribute("mnovoUsuario", "Novo Usuário");
        return "novoUsuario";
    }

    // Requisições
    @RequestMapping(value = "/CriarUsuario", method = RequestMethod.POST)
    public String Create(Model modelo, String razaoSocial, String email, String senha, String endereco, String CP,
            String telefone) throws SQLException {
        conn = new ConexaoDAO().Connect();
        UsuarioDTO user = new UsuarioDTO();
        user.setRazaoSocial(razaoSocial);
        user.setEmail(email);
        user.setSenha(senha);
        user.setTelefone(telefone);
        user.setEndereco(endereco);
        user.setCP(CP);

        UsuarioDAO obj = new UsuarioDAO();

        obj.CriarUsuario(user);
        obj.ListarUsuarios(conn);

        return "redirect:/";
    }

    @PostMapping("/usuario/{id}")
    public String deletarUsuario(@PathVariable int id) throws SQLException {
        UsuarioDAO repository = new UsuarioDAO();
        repository.deletarUsuario(id);
        return "redirect:/home";
    }

}
