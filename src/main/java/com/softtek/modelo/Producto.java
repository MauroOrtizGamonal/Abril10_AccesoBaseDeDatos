package com.softtek.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Producto {
    private int idProducto;
    private String nombreProducto;
    private int idProveedor;
    private int idCategoria;
    private String cantidadPorUnidad;
    private double precioUnitario;
    private int unidadesStock;
    private int unidadesPedido;
    private int nivelReorden;
    private int discontinuidad;

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", unidadesStock=" + unidadesStock +
                '}';
    }
}
