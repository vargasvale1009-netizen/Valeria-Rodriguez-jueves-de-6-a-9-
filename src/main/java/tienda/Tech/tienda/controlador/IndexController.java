package tienda.Tech.tienda.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tienda.Tech.tienda.modelo.Categoria;
import tienda.Tech.tienda.modelo.Producto;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) Integer categoriaId,
            Model model) {

        // Categorías activas para las tabs
        List<Categoria> categoriasActivas = CategoriaController.getCategorias()
                .stream()
                .filter(Categoria::getActivo)
                .collect(Collectors.toList());

        // Productos filtrados por categoría (o todos si no hay filtro)
        List<Producto> productosMostrados;
        if (categoriaId != null) {
            productosMostrados = ProductoController.getProductos()
                    .stream()
                    .filter(p -> p.getCategoriaId().equals(categoriaId))
                    .collect(Collectors.toList());
        } else {
            productosMostrados = ProductoController.getProductos();
        }

        model.addAttribute("categoriasActivas", categoriasActivas);
        model.addAttribute("productos", productosMostrados);
        model.addAttribute("categoriaSeleccionada", categoriaId);
        return "index";
    }
}
