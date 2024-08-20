package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CategoryDto;
import org.example.entity.Category;
import org.example.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryCRUDService implements CRUDService<CategoryDto> {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto getById(Integer id) {
        log.info("Get by id " + id);
        return mapToDto(categoryRepository.findById(id).orElseThrow());
    }

    @Override
    public Collection<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(CategoryDto item) {
        Category category = mapToEntity(item);
        categoryRepository.save(category);
    }

    @Override
    public void update(CategoryDto item) {
        categoryRepository.save(mapToEntity(item));
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public static Category mapToEntity (CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setNews(categoryDto.getNewsDto()
                .stream().map(NewsCRUDService::mapToEntity).toList());
        return category;
    }

    public static CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setNewsDto(category.getNews()
                .stream()
                .map(NewsCRUDService::mapToDto).toList());
        return categoryDto;
    }
}
