package org.example.cinema.web;


import jakarta.servlet.http.HttpServletRequest;
import org.example.cinema.model.TicketCart;
import org.example.cinema.service.MovieService;
import org.example.cinema.service.TicketOrderService;
import org.example.cinema.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticketCart")
public class TicketCartController {

    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;
    private final UserService userService;

    public TicketCartController(TicketOrderService ticketOrderService, MovieService movieService, UserService userService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
        this.userService = userService;
    }

   @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String username = req.getRemoteUser();
        TicketCart ticketCart = this.ticketOrderService.getActiveTicketCart(username);
        model.addAttribute("movies", this.ticketOrderService.listAllMoviesInTicketCart(ticketCart.getId()));
        model.addAttribute("bodyContent", "ticket_cart");
        return "master";
    }

    @PostMapping("/add-movie/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req) {
        try {
            String username = req.getRemoteUser();
            TicketCart ticketCart = this.ticketOrderService.addMovieToTicketCart(username, id);
            return "redirect:/ticketCart";
        } catch (RuntimeException exception) {
            return "redirect:/ticketCart?error=" + exception.getMessage();
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpServletRequest req) {
        try {
            String username = req.getRemoteUser();
            TicketCart ticketCart = this.ticketOrderService.deleteMovieFromTicketCart(username, id);
            return "redirect:/ticketCart";
        } catch (RuntimeException exception) {
            return "redirect:/ticketCart?error=" + exception.getMessage();
        }
    }


}
