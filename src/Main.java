import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        List<Venta> ventas = new ArrayList<>();


        try {
            for (String lineaEntera : Files.readAllLines(Paths.get("src/productos.cvs"))) {
                String[] lineaCortada = lineaEntera.split(";");
                productos.add(new Producto(
                        Integer.parseInt(lineaCortada[0]),
                        lineaCortada[1],
                        Float.parseFloat(lineaCortada[2])));
            }


            for (String lineaEntera : Files.readAllLines(Paths.get("src/ventas.cvs"))) {
                String[] lineaCortada = lineaEntera.split(";");
                ventas.add(new Venta(
                        Integer.parseInt(lineaCortada[0]),
                        Float.parseFloat(lineaCortada[1])));
            }

        } catch (IOException e) {
            System.out.println("Error leyendo archivo");
        }

/*
        productos:
        1 : sal a 5
        2 : azucar a 15
        3 : aceite a 25

        ventas:
        2 : cantidad 3 = 45
        1 : cantidad 1 = 5
        3 : cantidad 2 = 50
        2 : cantidad 5 = 30

*/
        /*
        venta = pronostico
        productos = rondas.partidos

        pronostico va a tener que hacer un bucle dentro de rondas, dentro otro bucle
        Y después comparar el partido del pronostico con el partido de la ronda

        */
        float sumaDeVentas = 0;
        for (int v = 0; v < ventas.size(); v++) {
            Venta estaVenta = ventas.get(v);
            for (int p = 0; p < productos.size(); p++) {
                Producto esteProducto = productos.get(p);
                if (esteProducto.codigo == estaVenta.codigo) {
                    estaVenta.total = estaVenta.cantidad * esteProducto.precio;
                    sumaDeVentas += estaVenta.total;
                }
            }
        }


        System.out.println("El total de ventas es: " + ventas.size() + " por un total de $" + sumaDeVentas);

        for (int v = 0; v < ventas.size(); v++) {
            Venta estaVenta = ventas.get(v);
            for (int p = 0; p < productos.size(); p++) {
                Producto esteProducto = productos.get(p);
                if (esteProducto.codigo == estaVenta.codigo) {
                    System.out.println("Se vendió el producto [" + esteProducto.nombre + "] ($" + esteProducto.precio + ") x " + estaVenta.cantidad + " unidades a $" + estaVenta.total);
                }
            }
        }

    }
}
