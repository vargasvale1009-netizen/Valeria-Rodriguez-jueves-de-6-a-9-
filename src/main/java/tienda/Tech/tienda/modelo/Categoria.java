package tienda.Tech.tienda.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

    private Integer id;
    private String descripcion;
    private String imagen;   // URL o ruta de la imagen
    private Boolean activo;  // true = Activa, false = Inactiva
}
