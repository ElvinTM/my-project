package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.NewsDto;
import org.example.entity.Category;
import org.example.entity.News;
import org.example.repository.CategoryRepository;
import org.example.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class NewsCRUDService implements CRUDService<NewsDto> {

    private final NewsRepository newsRepository;

    private final CategoryRepository categoryRepository;


    @Override
    public NewsDto getById(Integer id) {
        News news = newsRepository.findById(id).orElseThrow();
        return mapToDto(news);
    }

    @Override
    public Collection<NewsDto> getAll() {
        return newsRepository.findAll()
                .stream()
                .map(NewsCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(NewsDto newsDto) {
        News news= mapToEntity(newsDto);
        String name = newsDto.getCategoryName();
        Category category = categoryRepository.findAllByNameContaining(name).orElseThrow();
        news.setCategory(category);
        newsRepository.save(news);
    }

    @Override
    public void update(NewsDto newsDto) {
        News news= mapToEntity(newsDto);
        String name = newsDto.getCategoryName();
        Category category = categoryRepository.findAllByNameContaining(name).orElseThrow();
        news.setCategory(category);
        newsRepository.save(news);
    }

    @Override
    public void deleteById(Integer id) {
        newsRepository.deleteById(id);
    }

    public static NewsDto mapToDto (News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        newsDto.setText(news.getText());
        newsDto.setCategoryName(news.getCategory().getName());
        newsDto.setDate(news.getDate());
        return newsDto;
    }

    public static News mapToEntity (NewsDto newsDto) {
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        return news;
    }
}
