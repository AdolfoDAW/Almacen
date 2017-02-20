package Negocio;

import Modelo.Cliente;
import Modelo.Lavadora;
import Modelo.Mayorista;
import Modelo.Mueble;
import Modelo.Particular;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.TipoMayorista;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class MenuPrincipal {

    NegociosService servicio;

    MenuPrincipal() {
        servicio = new NegociosService();
    }

    public void inciarAplicacion() {
        try {
            // menu Principal
            int opcion = -1;
            do {
                System.out.println("1.Productos");
                System.out.println("2.Clientes");
                System.out.println("3.Ventas");
                System.out.println("0. Para Salir");
                System.out.println("Introduzca la opcion");
                Scanner sc = new Scanner(System.in);
                opcion = sc.nextInt();
                if (opcion == 1) {
                    menuProductos();
                }
                if (opcion == 2) {
                    menuClientes();
                }
                if (opcion == 3) {
                    menuVentas();
                }

            } while (opcion != 0);

            System.out.println("Gracias por usar nuestra aplicacion");
            System.out.println("Hasta la proxima");

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.inciarAplicacion();
        }
    }

    private void menuProductos() {
        try {
            int opcionProductos = -1;
            do {
                System.out.println("1.Introducir Producto");
                System.out.println("2.Dar de baja un producto");
                System.out.println("3.Imprimir los datos de un producto");
                System.out.println("4.Imprimir todos los productos");
                System.out.println("0. Salir del menu");
                Scanner sc = new Scanner(System.in);
                opcionProductos = sc.nextInt();

                if (opcionProductos == 1) {
                    Producto p = datosProducto();
                    servicio.introducirProducto(p);
                }
                if (opcionProductos == 2) {
                    System.out.println("Introduzca el número de producto: ");
                    int num = sc.nextInt();
                    servicio.elimninarProducto(num);
                }
                if (opcionProductos == 3) {
                    System.out.println("Introduzca el número de producto: ");
                    int nprod = sc.nextInt();
                    System.out.println(servicio.buscarProducto(nprod));
                }
                if (opcionProductos == 4) {
                    System.out.println(servicio.imprimirTodosProductos());
                }

            } while (opcionProductos != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida" + e.getMessage());
            this.menuProductos();
        }
    }

    public Producto datosProducto() throws Exception {
        Scanner sc = new Scanner(System.in);
        Producto producto = null;
        String nombre;
        double precio;
        int opcion = -1;

        System.out.println("Introduzca el nombre: ");
        nombre = sc.nextLine();

        System.out.println("Introduzca precio base: ");
        precio = Double.parseDouble(sc.nextLine());
        do {
            System.out.println("Introduzca el tipo de producto: ");
            System.out.println("1. Mueble");
            System.out.println("2. Lavadora");
            System.out.println("3. Televisor");
            opcion = sc.nextInt();
        } while (opcion != 1 && opcion != 2 && opcion != 3);
        if (opcion == 1) {
            producto = pedirMueble();
        }
        if (opcion == 2) {
            producto = pedirLavadora();
        }
        if (opcion == 3) {
            producto = pedirTelevisor();
        }
        if (producto != null) {
            producto.setNombre(nombre);
            producto.setPrecio(precio);
        }

        return producto;
    }

    public Mueble pedirMueble() throws ParseException {
        Mueble m = new Mueble();
        Scanner sc = new Scanner(System.in);
        String fecha = "";
        m.setTipoMadera(pedirMadera());

        System.out.println("Introduzca el estilo:");
        m.setEstilo(sc.nextLine());

        try {
            System.out.println("Introduzca la fecha (dd/MMMM/yyyy): ");

            fecha = sc.next().toLowerCase();
            m.setAnyoFab(this.validarFecha(fecha));

            //throw new FormatoFechaErroneo(fecha);
        } catch (FormatoFechaErroneo e) {
            System.out.println("Fecha errónea");
        }
        return m;
    }

    public Lavadora pedirLavadora() {
        Scanner sc = new Scanner(System.in);
        Lavadora l = new Lavadora();
        double precio = l.getPrecio();

        System.out.println("Introduzca las revoluciones(rpm): ");
        int rev = Integer.parseInt(sc.nextLine());
        l.setRevoluciones(rev);

        System.out.println("Introduzca la capacidad (kg): ");
        int c = Integer.parseInt(sc.nextLine());
        l.setCarga(c);

        l.setPrecio(precio);

        return l;
    }

    public Televisor pedirTelevisor() {
        Televisor tv = new Televisor();
        Televisor.TipoTV t = null;
        String opcion;
        double precio = tv.getPrecio();
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca las pulgadas: ");
        double pulgadas = Double.parseDouble(sc.nextLine());
        tv.setPulgadas(pulgadas);

        do {

            System.out.println("Introduzca el tipo de televisor: ");
            System.out.println(Arrays.toString(Televisor.TipoTV.values()));
            System.out.println("1.PLASMA");
            System.out.println("2.LCD");
            System.out.println("3.LED");
            System.out.println("4.OLED");

            opcion = (sc.nextLine());
        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4"));

        if (opcion.equals("1")) {
            t = Televisor.TipoTV.PLASMA;
        }
        if (opcion.equals("2")) {
            t = Televisor.TipoTV.LCD;
        }
        if (opcion.equals("3")) {
            t = Televisor.TipoTV.LED;
        }
        if (opcion.equals("4")) {
            t = Televisor.TipoTV.OLED;
        }

        tv.setTipo(t);
        tv.setPrecio(precio);
        return tv;
    }

    private Mueble.Madera pedirMadera() {
        Mueble.Madera m = null;
        String opcion;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca el tipo de Madera");
            System.out.println(Arrays.toString(Mueble.Madera.values()));
            System.out.println("1.Pino");
            System.out.println("2.Roble");
            System.out.println("3.Haya");

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3"));

        if (opcion.equals("1")) {
            m = Mueble.Madera.PINO;
        }
        if (opcion.equals("2")) {
            m = Mueble.Madera.ROBLE;
        }
        if (opcion.equals("3")) {
            m = Mueble.Madera.HAYA;
        }
        return m;
    }

    private void menuClientes() {
        Scanner sc = new Scanner(System.in);

        try {
            int opcionClientes = -1;
            String opciontipocliente = "";
            Cliente cliente = null;

            do {
                System.out.println("1.Introducir Cliente");
                System.out.println("2.Dar de baja un cliente");
                System.out.println("3.Imprimir los datos de un cliente");
                System.out.println("4.Imprimir todos los clientes");
                System.out.println("0. Salir del menu");

                opcionClientes = sc.nextInt();

                if (opcionClientes == 1) {

                    System.out.println("Nombre:");
                    String nombre = sc.next();
                    System.out.println("Razón social:");
                    String razon = sc.next();

                    do {
                        System.out.println("1.Mayorista");
                        System.out.println("2.Particular");
                        opciontipocliente = sc.next();

                    } while (!opciontipocliente.equals("1") && !opciontipocliente.equals("2"));

                    if (opciontipocliente.equals("1")) {
                        cliente = pedirMayorista();
                        servicio.introducirCliente(cliente);
                    }

                    if (opciontipocliente.equals("2")) {
                        cliente = pedirParticular();
                        servicio.introducirCliente(cliente);
                    }

                    if (cliente != null) {
                        cliente.setNombre(nombre);
                        cliente.setRazonSocial(razon);
                    }

                }

                if (opcionClientes == 2) {
                    System.out.println("Introduzca el número del cliente a eliminar");
                    int numero = Integer.parseInt(sc.next());
                    servicio.eliminarCliente(numero);
                }
                if (opcionClientes == 3) {
                    System.out.println("Introduzca el número del cliente a buscar");
                    int numero = Integer.parseInt(sc.next());
                    Cliente c = servicio.buscarCliente(numero);
                    System.out.println(c.imprimir());
                }
                if (opcionClientes == 4) {
                    String resultado = servicio.imprimirTodosClientes();
                    System.out.println(resultado);
                }

            } while (opcionClientes != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuClientes();
        }

    }

    public Mayorista pedirMayorista() {
        Scanner sc = new Scanner(System.in);
        String opciontipomayorista = "";
        TipoMayorista tipo = null;
        Mayorista m = new Mayorista();

        System.out.println("CIF:");
        String cif = sc.next();

        System.out.println("Tipo:");

        do {
            System.out.println("1.Gran supeficie");
            System.out.println("2.Tienda");
            opciontipomayorista = sc.next();

        } while (!opciontipomayorista.equals("1") && !opciontipomayorista.equals("2"));

        if (opciontipomayorista.equals("1")) {
            tipo = TipoMayorista.GRAN_SUPERFICIE;
        }
        if (opciontipomayorista.equals("2")) {
            tipo = TipoMayorista.TIENDA;
        }

        System.out.println("Descuento:");
        double descuento = sc.nextDouble();

        m.setCif(cif);
        m.setTipoMayorista(tipo);
        m.setDescuento(descuento);
        return m;
    }

    public Particular pedirParticular() {
        Particular p = new Particular();
        Scanner sc = new Scanner(System.in);
        String dni = "";

        do {
            try {
                System.out.println("DNI:");
                dni = sc.next().toUpperCase();
                p.calcularDni(dni);

            } catch (ExcepcionDNI e) {
                System.out.println(e.getMessage());
            }
        } while (!p.calcularDni(dni));
        return p;
    }

    private void menuVentas() {
        Scanner sc = new Scanner(System.in);

        try {
            String opcionVentas = "-1";
            do {
                System.out.println("1.Introducir Venta");
                System.out.println("2.Dar de baja una venta");
                System.out.println("3.Imprimir los datos de una venta");
                System.out.println("4.Imprimir todas las ventas");
                System.out.println("0. Salir del menu");
                opcionVentas = sc.nextLine();

                if (opcionVentas.equals("1")) {
                    System.out.println("Introduce el número de cliente.");
                    int nv = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el número de producto.");
                    int np = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el nombre del vendedor: ");
                    String v = sc.nextLine();
                    servicio.introducirVenta(nv, np, v);
                }
                if (opcionVentas.equals("2")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    servicio.eliminarVenta(nv);
                }
                if (opcionVentas.equals("3")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    servicio.buscarVenta(nv);
                }
                if (opcionVentas.equals("4")) {
                    System.out.println(servicio.imprimirtodasVentas());
                }

            } while (!opcionVentas.equals("0"));

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuVentas();
        }

    }

    private LocalDate validarFecha(String fecha) throws ParseException {
        //SimpleDateFormat sdf;

        //sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
        LocalDate fec = null;
        try {
            fec = LocalDate.parse(fecha, sdf);
        } catch (DateTimeParseException e) {
            throw new FormatoFechaErroneo(fec);
        }
        return fec;
    }

}
