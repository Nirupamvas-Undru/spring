/*
package com.springboot.resortmanagement;


import com.springboot.resortmanagement.entity.Resort;
import com.springboot.resortmanagement.repository.ResortRepository;
import com.springboot.resortmanagement.service.ResortService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
/*
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResortServiceImplTest {

    @Autowired
    private ResortService resortService;

    @MockBean
    private ResortRepository resortRepository;

    @Test
    void listResorts(){
        when(resortRepository.findAll()).thenReturn(Stream
                .of(new Resort("Novtel Resort","Hyderabad",4.0)).collect(Collectors.toList()));
        assertEquals(1, resortService.findAll().size());
    }

    @Test
    void getResortById(){
        Resort resort = new Resort("Novtel Resort","Hyderabad",4.0);
        when(resortRepository.getById(2)).thenReturn(resort);

        Resort resort1 = resortService.findById(2);
        assertEquals("Novtel resort", resort1.getResortName());
        assertEquals("Hyderabad", resort1.getResortAddress());
        assertEquals(4.0, resort1.getResortRating());

    }

    @Test
    @SuppressWarnings("squid:S2699")
    void saveTestResort(){
        Resort resort = new Resort("Jungle Resort","Delhi",4.1);
        when(resortRepository.save(any(Resort.class))).thenReturn(resort);
    }

    @Test
    void DeleteTestResort(){
        int theId = 1;
        resortService.deleteById(theId);
        verify(resortRepository).deleteById(any());
    }

}
*/