package entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cargos")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_cargo", length = 100, nullable = false)
    private String nombre;

    @Column(name = "salario_cargo", nullable = false)
    private double salario;

    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Empleado> empleados = new ArrayList<>();
}