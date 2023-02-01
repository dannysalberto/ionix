package com.ionix.testdev.utils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ConvertDTO {
	
    private ModelMapper modelMapper;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List get(List dtos, Class p)
    {
        List data = new ArrayList();
        for (Object dto: dtos) {
            data.add(create(dto, p));
        }
    	return data;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Object create(Object o, Class p){        
        return modelMapper.map(o, p);
    }
    
    @PostConstruct
    void addConvertTypes() {
        modelMapper = new ModelMapper();
    }    

}
