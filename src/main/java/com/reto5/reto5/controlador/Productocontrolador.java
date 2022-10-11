
package com.reto5.reto5.controlador;

import com.reto5.reto5.modelo.Producto;
import com.reto5.reto5.modelo.ProductoRepositorio;
import com.reto5.reto5.vista.Vista2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class Productocontrolador implements ActionListener {
    
    
   ProductoRepositorio productorepositorio;
   
   Vista2 vista;
   
   public Productocontrolador(ProductoRepositorio productoRepositorio, Vista2 vista){
       this.productorepositorio = productoRepositorio;
       this.vista = vista;
       manejadorEventos();
       buscarProducto();
   }
   
   private void manejadorEventos(){
       
       vista.getBotonagregar().addActionListener(this);
       vista.getBotonactualizar1().addActionListener(this);
       vista.getBotonborrar().addActionListener(this);
       vista.getBotoninforme().addActionListener(this);
       vista.getActboton().addActionListener(this);
   }
   
   public void buscarProducto(){
       
       List<Producto> listaProductos = (List<Producto>) productorepositorio.findAll();
       JTable tablaproductos = vista.getTablaproductos();
       Integer Fila = 0;
       for (Producto producto : listaProductos) {
           tablaproductos.setValueAt(producto.getCodigo(), Fila, 0);
           tablaproductos.setValueAt(producto.getNombre(), Fila, 1);
           tablaproductos.setValueAt(producto.getPrecio(), Fila, 2);
           tablaproductos.setValueAt(producto.getInventario(), Fila, 3);
           Fila++;
       }
       
       for (int i = 0; i < tablaproductos.getRowCount(); i++) {
           tablaproductos.setValueAt((""), Fila, 0);
           tablaproductos.setValueAt((""), Fila, 1);
           tablaproductos.setValueAt((""), Fila, 2);
           tablaproductos.setValueAt((""), Fila, 3);
       }
       
       
   }
   
   public void agregar(){
      String nombre = vista.getCamponombre().getText();
      Integer inventario = Integer.parseInt(vista.getCampoinventario().getText());
      Double precio = Double.parseDouble(vista.getCampoprecio().getText());
      
      Producto producto = new Producto(null, nombre, precio, inventario);
      productorepositorio.save(producto);
      JOptionPane.showMessageDialog(null, "Producto creado correctamente", "CREADO",JOptionPane.INFORMATION_MESSAGE);
       
   }
   
   public void actualizar(){
       
      JTable tablaProductos = vista.getTablaproductos();
      Integer codigo = (Integer) tablaProductos.getModel().getValueAt(tablaProductos.getSelectedRow(), 0);
      String nombre = vista.getActnombretxt().getText();
      Integer inventario = Integer.parseInt(vista.getActinventariotxt().getText());
      Double precio = Double.parseDouble(vista.getActpreciotxt().getText());
      
      Producto producto = new Producto(codigo, nombre, precio, inventario);
      productorepositorio.save(producto);
      JOptionPane.showMessageDialog(null, "Producto actualizado correctamente", "ACTUALIZADO",JOptionPane.INFORMATION_MESSAGE);
   }
   
   public void eliminar(){
       JTable tablaproductos = vista.getTablaproductos();
       Integer codigoproducto = (Integer) tablaproductos.getModel().getValueAt(tablaproductos.getSelectedRow(), 0);
       
       if(codigoproducto == null){
           JOptionPane.showMessageDialog(null, "Por favor seleccione el producto a eliminar");
       }else {
           int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el producto seleccionado?");
           
           if(opcion != 1){
             
               
           productorepositorio.deleteById(codigoproducto);
           JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
           }
       }
   }
   
   
   public void generarInforme(){
    
   List<Producto> listaProductos = (List<Producto>) productorepositorio.findAll();    
   Double sumatoriaPrecios = 0.0;    
   String nombreprecioMayor = ("");
   String nombreprecioMenor = ("");
   Double precioMayor = 0.0;
   Double precioMenor = 9999999.9;
   Double sumainventario = 0.0;
   
   for (Producto producto : listaProductos){
       if(producto.getPrecio() > precioMayor){
           precioMayor = producto.getPrecio();
           nombreprecioMayor = producto.getNombre();
       }
       
       if(producto.getPrecio() < precioMenor){
           precioMenor = producto.getPrecio();
           nombreprecioMenor = producto.getNombre();
       }
       
       sumainventario += producto.getInventario()*producto.getPrecio();
       sumatoriaPrecios += producto.getPrecio();
          
       
       
   }
       
       Double promedio = sumatoriaPrecios/listaProductos.size();
       JOptionPane.showMessageDialog(null, "Producto precio mayor: "+ nombreprecioMayor +
               "\nProducto precio menor: "+ nombreprecioMenor +
               "\nPromedio precios: "+ String.format("%.1f",promedio) +
               "\nTotal de inventario: " + sumainventario);
     
  
       
   }
    private void llenarCampos() {
        JTable tablaProductos = vista.getTablaproductos();
        String nombre = (String) tablaProductos.getModel().getValueAt(tablaProductos.getSelectedRow(), 1);
        Double precio = (Double) tablaProductos.getModel().getValueAt(tablaProductos.getSelectedRow(), 2);
        Integer inventario = (Integer) tablaProductos.getModel().getValueAt(tablaProductos.getSelectedRow(), 3);
        
        vista.getActnombretxt().setText(nombre);
        vista.getActinventariotxt().setText(String.valueOf(inventario));
        vista.getActpreciotxt().setText(String.valueOf(precio));
   }

    @Override
    public void actionPerformed(ActionEvent evento) {
      
        if(evento.getSource() == vista.getBotonagregar()){
            if (vista.getCamponombre().getText().equals("")){
                
                JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del producto");
            }else if (vista.getCampoprecio().getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe ingresar el precio del producto");
            }else if (vista.getCampoinventario().getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe ingresar el inventario del producto");
            }else{
                agregar();
                buscarProducto();
            vista.getCamponombre().setText("");
            vista.getCampoinventario().setText("");
            vista.getCampoprecio().setText("");
            }
            
        }else if (evento.getSource() == vista.getBotonborrar()){
            eliminar();
            buscarProducto();
        }else if(evento.getSource() == vista.getBotoninforme()){
            generarInforme();
        }else if (evento.getSource() == vista.getBotonactualizar1()){
            llenarCampos();
            vista.getActualizarframe().setVisible(true);
            vista.getActualizarframe().setLocation(400, 300);
            vista.getActualizarframe().setSize(400, 300);
        }else if (evento.getSource() == vista.getActboton()){
            if (vista.getActnombretxt().getText().equals("")){
                 JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del producto");
            }else if (vista.getActpreciotxt().getText().equals("")){
                 JOptionPane.showMessageDialog(null, "Debe ingresar el precio del producto");
            }else if (vista.getActinventariotxt().getText().equals("")){
                JOptionPane.showMessageDialog(null, "Debe ingresar el inventario del producto");
            }else {
                
                actualizar();
                buscarProducto();
            }
        }

    }

 
   
   
   
   
   
}



