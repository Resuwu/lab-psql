package com.example.labpsql.rest;

import com.example.labpsql.auth.dto.SignInRequest;
import com.example.labpsql.auth.dto.SignUpRequest;
import com.example.labpsql.services.CountryService;
import com.example.labpsql.services.SubjectService;
import com.example.labpsql.services.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CountryService countryService;
    private final SubjectService subjectService;
    private final TeamService teamService;

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("message", "Welcome to the Login Page!");
        model.addAttribute("signInRequest", new SignInRequest());
        return "login";
    }

    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("message", "Welcome to the Registration Page!");
        model.addAttribute("signUpRequest", new SignUpRequest());
        return "register";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Home Page!");
        return "home";
    }

    @GetMapping("/countries")
    public String countries(Model model) {
        model.addAttribute("countries", countryService.getAllCountries());
        return "countries";
    }

    @GetMapping("/subjects")
    public String subjects(Model model) {
        model.addAttribute("subjects", subjectService.getAllSubjects());
        return "subjects";
    }

    @GetMapping("/teams")
    public String teams(Model model) {
        model.addAttribute("teams", teamService.getAllTeams());
        return "teams";
    }
}