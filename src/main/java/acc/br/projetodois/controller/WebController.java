package acc.br.projetodois.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import acc.br.projetodois.model.Score;
import acc.br.projetodois.service.ScoreService;

@Controller
public class WebController {

    private final ScoreService scoreService;

    //injeção de dependência
    public WebController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    // Reseta o score  
    @PostMapping("/score/reset")
    public String resetScore(Model model) {
        scoreService.resetScore(); 
        model.addAttribute("score", scoreService.getScore());
        return "reset-score";  
    }

    // Retorna o score 
    @ResponseBody
    @GetMapping("/score")
    public Score getScore() {
        return scoreService.getScore();
    }

    // Processa a escolha 
    @GetMapping("/teste")
    public String teste(@RequestParam(name = "escolha") String aEscolha, Model model) {
        String saida = scoreService.processarEscolha(aEscolha);
        model.addAttribute("saida", saida);
        model.addAttribute("aEscolha", aEscolha);
        model.addAttribute("score", scoreService.getScore());
        return "resultado";  
    }
}
