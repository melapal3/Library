package fr.afcepf.ad1.library.controllers;

import fr.afcepf.ad1.library.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    //doit intercepter une URL

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books")//url invoqué coté client pour que la méthode soit exécutée
    public String getBooks(Model model){
        // elle renvoie un objet. on renvoie des données au client à travers le Model
        model.addAttribute("books",bookRepository.findAll()); //nom à appeler dans la vue, valeur
        return "books/list"; //dans le template on aura le directory books et une page html (list.html)
    }
}
