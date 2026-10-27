package com.example.pokemonAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "no vacio")
    @Size(min = 2, max = 50, message = "debe tener entre 2 y 50 caracteres")
    private String tipo;

    @NotBlank(message = "equipo no puede ser vacio")
    private String nombre;

    @NotNull(message = "el nivel es requerido")
    @Min(value = 1, message = "el valor minimo es 1")
    @Max(value = 100, message = "el valor maximo esd 100")
    private Integer nivel;

    @Min(value = 1, message = "el ataque minimo es 1")
    private Integer ataque;

    @Min(value = 0, message = "la defenza no puede ser negativa")
    private Integer defensa;

    @Size (max = 225, message = "la descripcion no puedes exeder los 225 caracteres")
    private String descripcion;

    @URL(message = "la imagen debe ser URL")
    private String imagenURL;

    @Email(message = "el correo debe tener un formato valido")
    private String correoEntrnador;

    @PastOrPresent(message = "la fecha de nacimiento no puede ser mayor a la actual")
    private LocalDate fechaNacimiento;


    @Positive(message = "el peso del pokemon debe ser mayor a 0")
    private Double peso;

    @Pattern( regexp = "^\\+?[0-9]{1,3}?[-.\\s]?\\(?[0-9]{3}\\)?[-.\\s]?[0-9]{3}[-.\\s]?[0-9]{4}$", message = "no se telefono")
    private String telefonoEntrenadir;

    @AssertTrue(message = "el pokemos dene estar sano")
    private Boolean estaSano;
}
