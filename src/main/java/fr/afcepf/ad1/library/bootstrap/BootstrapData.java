package fr.afcepf.ad1.library.bootstrap;

import fr.afcepf.ad1.library.model.Author;
import fr.afcepf.ad1.library.model.Book;
import fr.afcepf.ad1.library.model.Publisher;
import fr.afcepf.ad1.library.repositories.AuthorRepository;
import fr.afcepf.ad1.library.repositories.BookRepository;
import fr.afcepf.ad1.library.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sun.net.www.protocol.http.AuthenticationHeader;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception { //va s'éxecuter après l'initialisation de l'appli
        System.out.println("Started in BootStrap");

        Author rod = new Author("Rod", "Johnson");
        Book noEjb = new Book("J2EE Development without EJB","123124");
        Publisher negrier = new Publisher("Négrier", "128 main street", "Boston", "Massachusetts", "02523565");
        publisherRepository.save(negrier);
        rod.getBooks().add(noEjb);
        noEjb.getAuthors().add(rod);
        noEjb.setPublisher(negrier);
        negrier.getBooks().add(noEjb);
        authorRepository.save(rod); // = persist en JPA
        bookRepository.save(noEjb);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "789123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(negrier);
        negrier.getBooks().add(ddd);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        publisherRepository.save(negrier);

        System.out.println("Number of books : " + bookRepository.count()); //count = renvoyer le nb d'entrées dans la table
        
    }
}
