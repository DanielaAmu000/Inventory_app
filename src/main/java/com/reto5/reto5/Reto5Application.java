package com.reto5.reto5;

import com.reto5.reto5.controlador.Productocontrolador;
import com.reto5.reto5.modelo.ProductoRepositorio;
import com.reto5.reto5.vista.Vista2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Reto5Application {
    
        @Autowired
        ProductoRepositorio productoRepositorio;
    
	public static void main(String[] args) {
		//SpringApplication.run(Reto5Application.class, args);
                new SpringApplicationBuilder(Reto5Application.class).headless(false).run(args);
	}

        
        @Bean
        public void applicationRunner(){
            
        //Vista2 vista = new Vista2();
        //vista.setVisible(true);
            
        new Productocontrolador(productoRepositorio, new Vista2());
        
       
        }
}
