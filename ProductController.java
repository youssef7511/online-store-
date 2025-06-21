package com.fsb.onlinestore.web.controllers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsb.onlinestore.business.services.ProductService;
import com.fsb.onlinestore.dao.entities.Product;
import com.fsb.onlinestore.web.models.requests.ProductForm;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")

public class ProductController {
   /* private static List<Product> products = new ArrayList<Product>();
    private static Long idCount = 0L;

    static {
        products.add(new Product(++idCount, "SS-S9", "Samsung Galaxy S9", 500D, 50, "samsung-s9.png"));
        products.add(new Product(++idCount, "NK-5P", "Nokia 5.1 Plus", 60D, 60, null));
        products.add(new Product(++idCount, "IP-7", "iPhone 7", 600D, 30, "iphone-7.png"));
    }*/
    //injection par attribut
  /*  @Autowired
    private ProductService productService;*/

    //injection par contructeur
    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }


   
    // Create
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("productForm") @Valid ProductForm productForm,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "create";
        }

        Product product = new Product(null, productForm.getCode(), productForm.getName(),
                productForm.getPrice(), productForm.getQuantity(), null);
        //products.add(product);
        try {
            this.productService.addProduct(product);
        } catch (DataIntegrityViolationException e) {
         model.addAttribute("duplicatedCode", "Error Duplicated Code");
         return "create";
        }
       

        return "redirect:/products";
    }

    // Read
    @GetMapping
    public String showProductList(Model model) {
        model.addAttribute("products",this.productService.getAllProduct());
        return "list";
    }

    // Update
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = this.productService.getProduct(id);
        if (product == null) {
            // Handle product not found
            return "redirect:/products";
        }

        ProductForm productForm = new ProductForm( product.getCode(), product.getName(),
                product.getPrice(), product.getQuantity(), product.getImage());
        model.addAttribute("productForm", productForm);
        model.addAttribute("id", product.getId());
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable("id") Long id,
            @ModelAttribute("productForm") @Valid ProductForm productForm,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        Product product = this.productService.getProduct(id);
        if (product == null) {
            // Handle product not found
            return "redirect:/products";
        }

        product.setCode(productForm.getCode());
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setQuantity(productForm.getQuantity());
        product.setImage(productForm.getImage());

        try {
            this.productService.updatePorduct(id, product);
        } catch (DataIntegrityViolationException e) {
         model.addAttribute("duplicatedCode", "Error Duplicated Code");
         return "edit";
        }

        return "redirect:/products";
    }

    // Delete
    @PostMapping("/{id}/delete") 
    public String deleteProduct(@PathVariable("id") Long id) {
        /*Product productToRemove = null;
        for (Product product : products) {
            if (product.getId().equals(id)) {
                productToRemove = product;
                break;
            }
        }

        if (productToRemove != null) {
            products.remove(productToRemove);
        } else {
            // Handle product not found
            return "redirect:/products";
        }*/
        this.productService.deleteProduct(id);

        return "redirect:/products";
    }

    // Helper method to get a product by its ID
   /* private Product getProductById(Long id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }*/
}
