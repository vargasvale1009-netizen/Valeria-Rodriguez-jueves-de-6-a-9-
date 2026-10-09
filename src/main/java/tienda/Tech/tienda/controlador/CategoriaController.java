package tienda.Tech.tienda.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tienda.Tech.tienda.modelo.Categoria;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    // Lista en memoria que simula la base de datos
    private static final List<Categoria> categorias = new ArrayList<>();

    static {
        categorias.add(new Categoria(1, "Monitores",    "/img/monitores.jpg",    true));
        categorias.add(new Categoria(2, "Teclados",     "/img/teclados.jpg",     true));
        categorias.add(new Categoria(3, "Tarjeta Madre","/img/tarjeta-madre.jpg",true));
        categorias.add(new Categoria(4, "Celulares",    "/img/celulares.jpg",    false));
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());
        return "categoria/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        categorias.removeIf(c -> c.getId().equals(id));
        return "redirect:/categoria/listado";
    }

    @GetMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, Model model) {
        Categoria categoria = categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("categoria", categoria);
        return "categoria/listado";  // por ahora redirige al listado
    }
}
