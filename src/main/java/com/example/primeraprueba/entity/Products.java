package com.example.primeraprueba.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.boot.registry.selector.spi.StrategySelectionException;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Data
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Integer id;

    @Column(name = "ProductName")
    private String name;

    @ManyToOne
    @JoinColumn(name = "SupplierID")
    private Suppliers supplier;

    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Categories category;

    @Column(name = "QuantityPerUnit")
    private String Quantity;

    @Column(name = "UnitPrice")
    private double Price;

    @Column(name = "UnitsInStock")
    private int Stock;

    @Column(name = "UnitsOnOrder")
    private int UnitsOnOrder;

    @Column(name = "ReorderLevel")
    private int ReorderLevel;

    @Column(name = "Discontinued")
    private int Discontinued;

    @Column(name = "Description")
    private String Description;
}
