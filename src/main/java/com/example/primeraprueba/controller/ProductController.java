package com.example.primeraprueba.controller;

import com.example.primeraprueba.entity.Categories;
import com.example.primeraprueba.entity.Products;
import com.example.primeraprueba.repository.CategoryRepository;
import com.example.primeraprueba.repository.ProductRepository;
import com.example.primeraprueba.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;


    @GetMapping("/lista")
    public String listar(Model model) {

    var listProductos = productRepository.findAll();
    model.addAttribute("productos", listProductos);
    return "lista-productos";
    }

    @GetMapping("/lista-proveedores")
    public String listarPorProveedor(Model model) {

        var listaConteo = productRepository.getProdutosPorProveedor();

        model.addAttribute("proveedores", listaConteo);

        return "informe-proveedores";
    }

    @GetMapping("/editar")
    public String editarProducto(@RequestParam("id") int id, Model model) {

        Optional<Products> optProduct = productRepository.findById(id);
        if (optProduct.isPresent()) {
            model.addAttribute("producto", optProduct.get());
            model.addAttribute("categorias", categoryRepository.findAll());
            model.addAttribute("proveedores", supplierRepository.findAll());

            return "editar-producto";
        }
        else{
            return "redirect:/productos/lista";
        }

    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Products product) {

        productRepository.modificarProducto(product.getPrice(), product.getStock(), product.getId());

        return "redirect:/productos/lista";
    }

    @GetMapping("/borrar")
    public String borrarProducto(@RequestParam("id") int id, Model modoel) {
        Optional<Products> optProduct = productRepository.findById(id);

        if (optProduct.isPresent()) {
            Products product = optProduct.get();

            // 2. En lugar de delete, cambiamos el estado
            // 1 significa "Descontinuado" en Northwind
            product.setDiscontinued(1);

            // 3. Guardamos el cambio
            productRepository.save(product);
        }

        // 4. Redirigimos a la lista
        return "redirect:/productos/lista";
    }

    @GetMapping("/crear")
    public String crearProducto(Model model) {
        model.addAttribute("producto", new Products());
        model.addAttribute("categorias", categoryRepository.findAll());
        model.addAttribute("proveedores", supplierRepository.findAll());
        return "crear-producto";
    }
}