package tienda.Tech.tienda.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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

    // Acceso estático para otros controladores (ej. IndexController)
    public static List<Categoria> getCategorias() {
        return categorias;
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

    @PostMapping("/actualizar")
    public String actualizarPost(
            @RequestParam Integer id,
            @RequestParam String descripcion,
            @RequestParam(required = false) String activo,
            @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile) {

        categorias.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .ifPresent(c -> {
                    c.setDescripcion(descripcion);
                    c.setActivo("true".equals(activo));
                    // Si se sube un archivo nuevo, usar su nombre; de lo contrario mantener la imagen actual
                    if (imagenFile != null && !imagenFile.isEmpty()) {
                        c.setImagen("/img/" + imagenFile.getOriginalFilename());
                    }
                });

        return "redirect:/categoria/listado";
        /*Semana 6 */
    }
}
