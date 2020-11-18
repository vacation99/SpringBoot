package com.example.daniil.SpringBootTestApp.Controllers;

import com.example.daniil.SpringBootTestApp.Models.Currency;
import com.example.daniil.SpringBootTestApp.Models.History;
import com.example.daniil.SpringBootTestApp.Repo.CurrencyRepo;
import com.example.daniil.SpringBootTestApp.Repo.HistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    CurrencyRepo currencyRepo;
    @Autowired
    HistoryRepo historyRepo;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Currency> currencies = currencyRepo.findAll();
        model.addAttribute("model", currencies);
        return "main";
    }

    @PostMapping("/main")
    public String convert(@RequestParam("currencyName1") String currencyName1,
                          @RequestParam("amountCurrency") String amountCurrency,
                          @RequestParam("currencyName2") String currencyName2,
                          Model model) {
        Currency currency1 = currencyRepo.findByCharCode(parseName(currencyName1));
        Currency currency2 = currencyRepo.findByCharCode(parseName(currencyName2));
        String one = currency1.getValue();
        String two = currency2.getValue();
        int nominal1 = Integer.parseInt(currency1.getNominal());
        int nominal2 = Integer.parseInt(currency2.getNominal());
        double result = Double.parseDouble(parseDot(one)) / Double.parseDouble(parseDot(two));
        result = result * Integer.parseInt(amountCurrency) / nominal1 * nominal2;
        History history = new History(currencyName1, currencyName2, amountCurrency, result);
        historyRepo.save(history);
        return "redirect:/history";
    }

    @GetMapping("/history")
    public String history(Model model) {
        Iterable<History> histories = historyRepo.findAll();
        model.addAttribute("model", histories);
        return "history";
    }

    @GetMapping("/clearHistory")
    public String clearHistory() {
        historyRepo.deleteAll();
        return "redirect:/history";
    }

    private String parseDot(String number) {
        char[] numberWithDot = number.toCharArray();
        String line = "";
        for (int i = 0; i < numberWithDot.length; i++) {
            if (numberWithDot[i] == ',') {
                line += ".";
            } else {
                line += numberWithDot[i];
            }
        }
        return line;
    }

    private String parseName(String name) {
        String line = "";
        char[] la = name.toCharArray();
        for (int i = 0; i < la.length; i++) {
            if (la[i] == ' ') {
                break;
            }
            else
                line += la[i];
        }
        return line;
    }
}