package tienda.Tech.tienda.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    private Integer id;
    private String descripcion;
    private String detalle;      // Descripción larga / detalle del producto
    private Double precio;
    private Integer existencias;
    private String imagen;       // Ruta de la imagen
    private Integer categoriaId; // ID de la categoría
    private Boolean activo;      // true = Activa, false = Inactiva
}
