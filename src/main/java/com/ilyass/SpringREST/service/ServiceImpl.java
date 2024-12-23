package com.ilyass.SpringREST.service;

import com.ilyass.SpringREST.dao.IDao;
import com.ilyass.SpringREST.domain.ArticleConverter;
import com.ilyass.SpringREST.domain.ArticleDTO;
import com.ilyass.SpringREST.service.exception.BusinessException;
import com.ilyass.SpringREST.service.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl implements IService{

    private IDao dao;

    public ServiceImpl(IDao dao) {
        this.dao = dao;
    }

    @Override
    public ArticleDTO getById(Long id) {
        return ArticleConverter.toDTO(dao.findById(id));
    }

    @Override
    public List<ArticleDTO> getAll() {
        return ArticleConverter.toDTOs(dao.findAll());
    }

    @Override
    public void create(ArticleDTO article) {
        Article articleFound = dao.findAll().stream().filter(a -> article.getId().equals(a.getId())).findFirst().orElse(null);
        if (articleFound != null)
            throw new BusinessException("Article with the same Id=" + article.getId() + " exist in database");
        dao.save(ArticleConverter.toBO(article));
    }

    @Override
    public void update(Long id, ArticleDTO article) {
        article.setId(id);
        dao.save(ArticleConverter.toBO(article));
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
