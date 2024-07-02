package org.example.ecomsb;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    private List<CartItem> cart = new CopyOnWriteArrayList<>();

    // Endpoint to retrieve all items in the cart
    @GetMapping
    public List<CartItem> getAllItems() {
        return cart;
    }

    // Endpoint to retrieve a specific item in the cart
    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getItemById(@PathVariable int id) {
        Optional<CartItem> item = cart.stream()
                .filter(i -> i.getId() == id)
                .findFirst();

        return item.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint to add a new item to the cart
    @PostMapping
    public ResponseEntity<CartItem> addItem(@RequestBody CartItem item) {
        cart.add(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    // Endpoint to update an existing item in the cart
    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateItem(@PathVariable int id, @RequestBody CartItem updatedItem) {
        for (CartItem item : cart) {
            if (item.getId() == id) {
                item.setName(updatedItem.getName());
                item.setQuantity(updatedItem.getQuantity());
                item.setPrice(updatedItem.getPrice());
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint to remove an item from the cart
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable int id) {
        cart.removeIf(item -> item.getId() == id);
        return ResponseEntity.noContent().build();
    }
}