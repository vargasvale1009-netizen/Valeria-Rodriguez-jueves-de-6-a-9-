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
import tienda.Tech.tienda.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    // Lista en memoria que simula la base de datos
    private static final List<Producto> productos = new ArrayList<>();

    // Categorías disponibles para el select del modal
    private static final List<Categoria> categorias = new ArrayList<>();

    static {
        categorias.add(new Categoria(1, "Monitores",     "/img/monitores.jpg",     true));
        categorias.add(new Categoria(2, "Teclados",      "/img/teclados.jpg",      true));
        categorias.add(new Categoria(3, "Tarjeta Madre", "/img/tarjeta-madre.jpg", true));
        categorias.add(new Categoria(4, "Celulares",     "/img/celulares.jpg",     false));

        productos.add(new Producto(1,  "Monitor AOC 19",          "Monitor LED 19 pulgadas, Full HD",           23000.0, 5,  "/img/monitor-aoc.jpg",     1, true));
        productos.add(new Producto(2,  "Monitor MAC",              "Monitor Apple 27 pulgadas Retina",           27000.0, 2,  "/img/monitor-mac.jpg",     1, true));
        productos.add(new Producto(3,  "Monitor Flex 21",          "Monitor curvo 21 pulgadas gaming",           24000.0, 5,  "/img/monitor-flex21.jpg",  1, true));
        productos.add(new Producto(4,  "Monitor Flex 36",          "Monitor ultrawide 36 pulgadas",              27600.0, 2,  "/img/monitor-flex36.jpg",  1, true));
        productos.add(new Producto(5,  "Teclado español everex",   "Teclado USB español layout",                 45000.0, 5,  "/img/teclado-everex.jpg",  2, true));
        productos.add(new Producto(6,  "Teclado Logitech K120",    "Teclado USB membrana Logitech",              18000.0, 8,  "/img/teclado-logi.jpg",    2, true));
        productos.add(new Producto(7,  "Teclado mecánico HyperX",  "Teclado mecánico switches Cherry MX Red",   65000.0, 3,  "/img/teclado-hyperx.jpg",  2, true));
        productos.add(new Producto(8,  "Tarjeta Madre ASUS B450",  "Tarjeta madre AMD B450 socket AM4",          85000.0, 4,  "/img/tm-asus.jpg",         3, true));
        productos.add(new Producto(9,  "Tarjeta Madre MSI B550",   "Tarjeta madre AMD B550 con PCIe 4.0",        92000.0, 2,  "/img/tm-msi.jpg",          3, true));
        productos.add(new Producto(10, "Samsung Galaxy A54",       "Smartphone Android 6.4 pulgadas 128GB",      95000.0, 6,  "/img/samsung-a54.jpg",     4, true));
        productos.add(new Producto(11, "Xiaomi Redmi Note 12",     "Smartphone AMOLED 6.67 pulgadas 128GB",      72000.0, 4,  "/img/xiaomi-note12.jpg",   4, true));
        productos.add(new Producto(12, "iPhone 14",                "Smartphone Apple A15 Bionic 128GB",         195000.0, 2,  "/img/iphone14.jpg",        4, true));
        productos.add(new Producto(13, "Monitor AOC 24",           "Monitor IPS 24 pulgadas Full HD 75Hz",       35000.0, 3,  "/img/monitor-aoc24.jpg",   1, true));
        productos.add(new Producto(14, "Teclado Genius KB-125",    "Teclado económico USB español",              12000.0, 10, "/img/teclado-genius.jpg",  2, false));
        productos.add(new Producto(15, "Tarjeta Madre Gigabyte",   "Tarjeta madre Intel LGA1200 H410",           78000.0, 1,  "/img/tm-gigabyte.jpg",     3, false));
        productos.add(new Producto(16, "Samsung Galaxy S23",       "Smartphone flagship Snapdragon 8 Gen 2",    185000.0, 3,  "/img/samsung-s23.jpg",     4, true));
        productos.add(new Producto(17, "Monitor LG 27",            "Monitor IPS 27 pulgadas QHD 144Hz",          42000.0, 4,  "/img/monitor-lg27.jpg",    1, true));
    }

    // Acceso estático para otros controladores (ej. IndexController)
    public static List<Producto> getProductos() {
        return productos;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalProductos", productos.size());
        return "producto/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        productos.removeIf(p -> p.getId().equals(id));
        return "redirect:/producto/listado";
    }

    @PostMapping("/actualizar")
    public String actualizarPost(
            @RequestParam Integer id,
            @RequestParam Integer categoriaId,
            @RequestParam String descripcion,
            @RequestParam(required = false) String detalle,
            @RequestParam Double precio,
            @RequestParam Integer existencias,
            @RequestParam(required = false) String activo,
            @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile) {

        productos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .ifPresent(p -> {
                    p.setCategoriaId(categoriaId);
                    p.setDescripcion(descripcion);
                    p.setDetalle(detalle);
                    p.setPrecio(precio);
                    p.setExistencias(existencias);
                    p.setActivo("true".equals(activo));
                    if (imagenFile != null && !imagenFile.isEmpty()) {
                        p.setImagen("/img/" + imagenFile.getOriginalFilename());
                    }
                });

        return "redirect:/producto/listado";
    }
}
