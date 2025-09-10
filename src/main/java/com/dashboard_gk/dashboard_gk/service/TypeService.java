package com.dashboard_gk.dashboard_gk.service;

import com.dashboard_gk.dashboard_gk.interfaces.ITypeService;
import com.dashboard_gk.dashboard_gk.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dashboard_gk.dashboard_gk.model.Type;

@Service
public class TypeService implements ITypeService {

    @Autowired
    TypeRepository typeRepository;

    public Type createType(Type typeRequest){

        Type type = new Type();
        type.setDescription(typeRequest.getDescription());

        return typeRepository.save(type);
    }
}
