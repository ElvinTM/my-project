package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.CategoryDto;
import org.example.services.CategoryCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryCRUDService crudService;

    @GetMapping
    public Collection<CategoryDto> gatAll() {
        return crudService.getAll();
    }

    @PostMapping
    public void creat(@RequestBody CategoryDto categoryDto) {
        crudService.create(categoryDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        crudService.create(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        crudService.deleteById(id);
    }
}
