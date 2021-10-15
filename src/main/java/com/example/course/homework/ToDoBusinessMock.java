package com.example.course.homework;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class ToDoBusinessMock {

    @Mock
    ToDoService doService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testusing_Mocks() {

        List<String> combinedlist = Arrays.asList(" Use Core Java ", " Use Spring Core ", " Use w3eHibernate ", " Use Spring MVC ");
        when(doService.getTodos("dummy")).thenReturn(combinedlist);

        ToDoBusiness business = new ToDoBusiness(doService);

        List<String> alltd = business.getTodosforHibernate("dummy");

        System.out.println(alltd);
        assertEquals(1, alltd.size());
    }
}