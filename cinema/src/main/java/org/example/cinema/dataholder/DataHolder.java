package org.example.cinema.dataholder;

import jakarta.annotation.PostConstruct;
import org.example.cinema.model.*;
import org.example.cinema.repository.MovieRepository;
import org.example.cinema.repository.ProductionRepository;
import org.example.cinema.repository.TicketCartRepository;
import org.example.cinema.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<User> users = null;
    public static List<Production> productions = null;
    public static List<Movie> movies = null;
    public static List<TicketCart> ticketCarts = null;


   private final MovieRepository movieRepository;
   private final ProductionRepository productionRepository;
   private final TicketCartRepository ticketCartRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(MovieRepository movieRepository, ProductionRepository productionRepository, TicketCartRepository ticketCartRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
        this.ticketCartRepository = ticketCartRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        productions = new ArrayList<>();
        movies = new ArrayList<>();
        ticketCarts = new ArrayList<>();


        if (userRepository.count() == 0) {
            users.add(
                    new User(
                            "admin",
                            "admin",
                            "admin",
                            passwordEncoder.encode("admin"),
                            UserType.ROLE_ADMIN
                    ));
            users.add(
                    new User(
                            "regular1",
                            "regular1",
                            "regular1",
                            passwordEncoder.encode("regular"),
                            UserType.ROLE_REGULAR
                    ));
            users.add(
                    new User(
                            "regular2",
                            "regular2",
                            "regular2",
                            passwordEncoder.encode("regular2"),
                            UserType.ROLE_REGULAR
                    ));

            userRepository.saveAll(users);
        }

        if (productionRepository.count() == 0) {
            productions.add(new Production("Universal Pictures", "California", "Universal City"));
            productions.add(new Production("Paramount Pictures", "California", "Hollywood"));
            productions.add(new Production("Vardar Film","Macedonia", "Skopje"));
            productions.add(new Production("Warner Bros. Pictures", "California", "Burbank"));
            productionRepository.saveAll(productions);
        }


        if (movieRepository.count() == 0) {
            List<Production> productionList = productionRepository.findAll();

            movies.add(new Movie("Challengers", "Tashi, a former tennis prodigy turned coach, turned her husband into a champion. But to overcome a losing streak, he needs to face his ex-best friend and Tashi's ex-boyfriend.", 7.6, productionList.get(0)));
            movies.add(new Movie("Interstellar", "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.", 8.7, productionList.get(1)));
            movies.add(new Movie("Dune: Part Two", "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.", 8.6, productionList.get(2)));
            movies.add(new Movie("Oppenheimer", "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.", 8.3, productionList.get(3)));
            movies.add(new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", 9.2, productionList.get(3)));
            movieRepository.saveAll(movies);

        }

    }
}
