
package com.reto5.reto5.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("productos")
public class Producto {
    
    @Id
    protected Integer codigo;
    protected String nombre;
    protected Double precio;
    protected Integer inventario;
    
    public Producto (Integer codigo, String nombre, Double precio, Integer inventario) {
    
    this.codigo = codigo;
    this.nombre = nombre;
    this.precio = precio; 
    this.inventario = inventario; 
}

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getInventario() {
        return inventario;
    }

    public void setInventario(Integer inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", inventario=" + inventario + '}';
    }

   
    
    
    
    
}
