package org.example.controllers;

import org.example.dto.NewsDto;
import org.example.services.NewsCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsCRUDService newsCRUD;

    public NewsController(NewsCRUDService newsCRUD) {
        this.newsCRUD = newsCRUD;
    }

    @GetMapping("/{id}")
    public NewsDto getNewsById(@PathVariable Integer id){
        return newsCRUD.getById(id);
    }

    @GetMapping
    public Collection<NewsDto> getAllNews(){
        return newsCRUD.getAll();
    }

    @PostMapping
    public void creatNews(@RequestBody NewsDto news) {
        newsCRUD.create(news);
    }

    @PutMapping("/{id}")
    public void updateNews(@PathVariable Integer id, @RequestBody NewsDto news) {
        news.setId(id);
        newsCRUD.update(news);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Integer id) {
        newsCRUD.deleteById(id);
    }
}
