package com.dashboard_gk.dashboard_gk.service;

import com.dashboard_gk.dashboard_gk.interfaces.ICategoryService;
import com.dashboard_gk.dashboard_gk.interfaces.ITypeService;
import com.dashboard_gk.dashboard_gk.model.Category;
import com.dashboard_gk.dashboard_gk.model.Type;
import com.dashboard_gk.dashboard_gk.repository.CategoryRepository;
import com.dashboard_gk.dashboard_gk.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category createCategory(Category categoryRequest){

        Category category = new Category();
        category.setDescription(categoryRequest.getDescription());

        return categoryRepository.save(category);
    }
}
