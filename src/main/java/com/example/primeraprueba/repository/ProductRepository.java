package com.example.primeraprueba.repository;

import com.example.primeraprueba.DTO.ProductosPorProveedorDto;
import com.example.primeraprueba.entity.Products;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Products, Integer>{

    @Query(value = "SELECT s.CompanyName as NombreProveedor, COUNT(p.ProductID) as CantidadProductos " +
            "FROM products p " +
            "JOIN suppliers s ON p.SupplierID = s.SupplierID " +
            "GROUP BY s.CompanyName",
            nativeQuery = true)
    List<ProductosPorProveedorDto> getProdutosPorProveedor();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE products p SET p.UnitPrice = :Price, p.UnitsInStock = :Stock WHERE p.ProductID = :id")
    void modificarProducto(@Param("Price") double price ,@Param("Stock") int stock, @Param("id") int id);
}
