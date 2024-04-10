package com.softtek.presentacion;

import com.softtek.modelo.Producto;
import com.softtek.persistencia.AccesoProducto;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AccesoProducto accesoProducto = new AccesoProducto();

        try {
           Producto p = new Producto(100, "Producto prueba", 2, 2, null, 25, 200, 3, 5, 0);
try {
    System.out.println(accesoProducto.create(p) ? "Producto insertado con éxito." : "No se ha podido insertar el producto.");
} catch (SQLException e) {
    e.printStackTrace();
}

            //Actualizar un producto existente (supongamos que el ID 1 existe en la base de datos)
            Producto productoExistente = new Producto(1, "Producto Actualizado 2", 1, 1, "12 unidades", 20.0, 100, 10, 20, 0);
            accesoProducto.actualizarProducto(productoExistente);


            /*Eliminar un producto (supongamos que el ID 2 existe en la base de datos)
            int idProductoAEliminar = 100;
            accesoProducto.eliminarProducto(idProductoAEliminar);*/

            /* Buscar un producto por su ID (supongamos que el ID 3 existe en la base de datos)
            int idProductoABuscar = 3;
            Producto productoBuscado = accesoProducto.buscarProducto(idProductoABuscar);
            if (productoBuscado != null) {
                System.out.println("Producto encontrado: " + productoBuscado);
            } else {
                System.out.println("Producto no encontrado.");
            }*/

            // Obtener todos los productos
            List<Producto> productos = accesoProducto.read();

            if (productos.isEmpty()) {
                System.out.println("No se encontraron productos.");
            } else {
                System.out.println("Lista de productos:");
                for (Producto producto : productos) {
                    System.out.println(producto);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
