package com.example.gestionlogistique.web;

import com.example.gestionlogistique.dtos.ProduitDTO;
import com.example.gestionlogistique.entities.Produit;
import com.example.gestionlogistique.entities.StockeProduit;
import com.example.gestionlogistique.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProduitAPI {
    @Autowired
    private ProduitService produitService;

    @GetMapping("/logistiques/produits")
    public List<StockeProduit> getAllProduits(){

        return  produitService.getAllProduits();
    }
    @PostMapping("logistique/produits")
    public ProduitDTO saveProduit(@RequestBody ProduitDTO produitDTO){
        return produitService.saveProduit(produitDTO);
    }

    @DeleteMapping("logistique/produits/{id}")
    public  void deleteProduit (@PathVariable Long id){
        produitService.deleteProduit(id);
    }
    @GetMapping("logistique/produit/{id}")
    public ProduitDTO getProduitById(@PathVariable Long id){
        return produitService.getProduitById(id);
    }
    @PutMapping("logistique/produitt/{id}")
    public ProduitDTO updateProduit(@PathVariable Long id , @RequestBody ProduitDTO produitDTO){
        produitDTO.setId(id);
        return produitService.updateProduit(produitDTO);
    }

}
