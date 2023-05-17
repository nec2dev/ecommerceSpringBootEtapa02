package com.nec2solutions.ecommercespringbootetapas.controller;
import com.nec2solutions.ecommercespringbootetapas.model.Producto;
import com.nec2solutions.ecommercespringbootetapas.model.Usuario;
import com.nec2solutions.ecommercespringbootetapas.service.ProductoService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String read(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "pages/itemread";
    }

    @GetMapping("/create")
    public String create() { return "pages/itemcreate"; }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();
        LOGGER.info("Producto: {}", producto);
        model.addAttribute("producto", producto);
        return "pages/itemupdate";
    }

    @PostMapping("/update")
    public String update(Producto producto) {
        LOGGER.info("Producto: {}", producto);
        productoService.update(producto);
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productoService.delete(id);
        return "redirect:/productos";
    }

    @PostMapping("/save")
    public String save(Producto producto) {
        LOGGER.info("Producto: {}", producto);
        Usuario usuario = new Usuario(10001, "Salala 444", "nec2@gmail.com", "Nahuel Correa", "1234", "234524", "ADMIN", "Nano");
        producto.setUsuario(usuario);
        productoService.create(producto);
        return "redirect:/productos";}
}

