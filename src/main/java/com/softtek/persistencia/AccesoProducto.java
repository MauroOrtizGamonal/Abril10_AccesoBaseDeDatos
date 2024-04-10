package com.softtek.persistencia;

import com.softtek.modelo.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccesoProducto extends conexion {

    public boolean create(Producto p) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt;
        String sql = "INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    abrirConexion();

        pstmt = miConexion.prepareStatement(sql);
        pstmt.setInt(1, p.getIdProducto());
        pstmt.setString(2, p.getNombreProducto());
        pstmt.setInt(3, p.getIdProveedor());
        pstmt.setInt(4, p.getIdCategoria());
        pstmt.setString(5, p.getCantidadPorUnidad());
        pstmt.setDouble(6, p.getPrecioUnitario());
        pstmt.setInt(7, p.getUnidadesStock());
        pstmt.setInt(8, p.getUnidadesPedido());
        pstmt.setInt(9, p.getNivelReorden());
        pstmt.setInt(10, p.getDiscontinuidad());

        if(pstmt.executeUpdate()>0){
            return true;
        }
        return false;
    }
    public List<Producto> read() throws SQLException, ClassNotFoundException {
        Statement sentencia;
        ResultSet resultado;
        String sql = "SELECT * FROM products";
        abrirConexion();
        List<Producto> productos = new ArrayList<>();
        sentencia = miConexion.createStatement();
        resultado = sentencia.executeQuery(sql);
        while (resultado.next()){
            productos.add(new Producto(resultado.getInt("product_id"),
                    resultado.getString("product_name"),
                    resultado.getInt("supplier_id"),
                    resultado.getInt("category_id"),
                    resultado.getString("quantity_per_unit"),
                    resultado.getDouble("unit_price"),
                    resultado.getInt("units_in_stock"),
                    resultado.getInt("units_on_order"),
                    resultado.getInt("reorder_level"),
                    resultado.getInt("discontinued")));
        }
        return productos;
    }

    public void actualizarProducto(Producto producto) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "UPDATE products SET product_name = ?, unit_price = ?, units_in_stock = ? WHERE product_id = ?";
        PreparedStatement preparedStatement = miConexion.prepareStatement(sql);
        preparedStatement.setString(1, producto.getNombreProducto());
        preparedStatement.setDouble(2, producto.getPrecioUnitario());
        preparedStatement.setInt(3, producto.getUnidadesStock());
        preparedStatement.setInt(4, producto.getIdProducto());
        preparedStatement.executeUpdate();
        cerrarConexion();
    }

    public void eliminarProducto(int id) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "DELETE FROM products WHERE product_id = ?";
        PreparedStatement preparedStatement = miConexion.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        cerrarConexion();
    }

   /* public Producto buscarProducto(int id) throws SQLException, ClassNotFoundException {
        abrirConexion();
        String sql = "SELECT product_id, product_name, unit_price, units_in_stock FROM products WHERE product_id = ?";
        PreparedStatement preparedStatement = miConexion.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        Producto producto = null;
        ResultSet resultado = preparedStatement.executeQuery();
        if (resultado.next()) {
            producto = new Producto(resultado.getInt("product_id"), resultado.getString("product_name"), resultado.getDouble("unit_price"), resultado.getInt("units_in_stock"));
        }
        cerrarConexion();
        return producto;
    }*/

    /*public List<Producto> listarProductos() throws ClassNotFoundException, SQLException {
        abrirConexion();
        String sql = "SELECT product_id, product_name, unit_price, units_in_stock FROM products";
        Statement sentencia = miConexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(sql);
        List<Producto> productos = new ArrayList<>();
        while (resultado.next()) {
            productos.add(new Producto(resultado.getInt("product_id"), resultado.getString("product_name"), resultado.getDouble("unit_price"), resultado.getInt("units_in_stock")));
        }
        cerrarConexion();
        return productos;
    }*/
}
