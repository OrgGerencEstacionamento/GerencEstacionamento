package io.github.pi.gerencestacionamento.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yaml.snakeyaml.events.Event.ID;

import io.github.pi.gerencestacionamento.domain.client.Enum.TipoPlanoEnum;
import io.github.pi.gerencestacionamento.domain.client.Enum.TipoUsuarioEnum;
import io.github.pi.gerencestacionamento.domain.client.dto.ClientRequestDTO;
import io.github.pi.gerencestacionamento.domain.client.dto.ClientResponseDTO;
import io.github.pi.gerencestacionamento.domain.client.entity.Usuario;
import io.github.pi.gerencestacionamento.service.ClientService;
import io.github.pi.gerencestacionamento.validations.ClientValidation;
import jakarta.servlet.http.HttpSession;

@Controller
public class TestClientController {

    ClientService clientService;
    ClientValidation clientValidation;

    public TestClientController(ClientService clientService, ClientValidation clientValidation) {
        this.clientService = clientService;
        this.clientValidation = clientValidation;
    }

    @GetMapping
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        return mv;
    }

    @GetMapping("/register")
    public String register() {
        
        return "register/index";
    }


    @PostMapping("/create")
    public String create(ClientRequestDTO clientRequestDTO, HttpSession httpSession) {

        clientRequestDTO.setTipoPlanoEnum(TipoPlanoEnum.DIARIO);
        clientRequestDTO.setTipoUsuarioEnum(TipoUsuarioEnum.COMUM);

        Usuario usuario = clientService.create(ClientRequestDTO.fromUsuario(clientRequestDTO));

        if(usuario != null) {
            httpSession.setAttribute("id", usuario.getIdUsuario());
            return "redirect:/home";
        }
        
        return "redirect:/register/index";

    }

    @GetMapping("/login")
    public String loginScreen() {
        return "login/index";
    }

    @PostMapping("/login")
    public ModelAndView login(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        HttpSession httpSession) {
        
        ModelAndView mv = new ModelAndView("redirect:/home");

        try {

            Optional<Usuario> usuario = clientValidation.login(email, password);

            if(usuario.isPresent()) {
                var id = usuario.get().getIdUsuario();
                httpSession.setAttribute("id", id);
                return mv;
            }    

        } catch (Exception e) {
            mv.addObject("error", e.getMessage());
            mv.setViewName("login/index");
            return mv;
        }

        return mv;
    }

    @GetMapping("/home")
    public ModelAndView homePage(HttpSession httpSession) {
        ModelAndView mv = new ModelAndView("/home/index");

        var id = httpSession.getAttribute("id");

        if(id != null){
           mv.addObject("id", id);
           return mv;
        }

        mv.setViewName("/login/login");
        return mv;
    }

    @PostMapping("/vacancies")
    public ModelAndView vecancies(@RequestParam("id") Integer id) {
        ModelAndView mv = new ModelAndView("vacancies/index");

        Optional<ClientResponseDTO> usuario = clientService.findOne(id);

        if(usuario.isPresent()) {
            mv.addObject("usuario", usuario.get());
            return mv;
        }
        
        mv.setViewName("redirect:/login");
        return mv;
    }
}
