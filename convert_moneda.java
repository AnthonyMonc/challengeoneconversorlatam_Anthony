/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertidor_moneda_JOption;

import javax.swing.JOptionPane;

/**
 *
 * @author cristian
 */
public class convert_moneda {
    private static final double TIPO_CAMBIO_DOLAR_EURO = 0.82;
    private static final double TIPO_CAMBIO_LIBRA_EURO = 1.17;
    private static final double TIPO_CAMBIO_YEN_EURO = 0.0075;
    private static final double TIPO_CAMBIO_WON_EURO = 0.00067;

    public static void main(String[] args) {
        String[] monedas = {"Euro", "Dólar", "Libra", "Yen", "Won"};

        String monedaOrigen = (String) JOptionPane.showInputDialog(null, "Seleccione la moneda de su país:",
                "Conversor de Moneda", JOptionPane.PLAIN_MESSAGE, null, monedas, monedas[0]);

        String monedaDestino = (String) JOptionPane.showInputDialog(null, "Seleccione la moneda a convertir:",
                "Conversor de Moneda", JOptionPane.PLAIN_MESSAGE, null, monedas, monedas[0]);

        double cantidad = obtenerCantidad();

        double resultado = convertirMoneda(monedaOrigen, monedaDestino, cantidad);

        JOptionPane.showMessageDialog(null, String.format("%.2f %s son %.2f %s", cantidad, monedaOrigen, resultado, monedaDestino),
                "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    private static double obtenerCantidad() {
        String cantidadTexto = JOptionPane.showInputDialog(null, "Ingrese la cantidad a convertir \n" + "Use un pundo para los centavos:",
                "Conversor de Moneda", JOptionPane.PLAIN_MESSAGE);

        try {
            return Double.parseDouble(cantidadTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return obtenerCantidad();
        }
    }

    private static double convertirMoneda(String monedaOrigen, String monedaDestino, double cantidad) {
        double tipoCambio = obtenerTipoCambio(monedaOrigen, monedaDestino);

        return cantidad * tipoCambio;
    }

    private static double obtenerTipoCambio(String monedaOrigen, String monedaDestino) {
        if (monedaOrigen.equals(monedaDestino)) {
            return 1.0;
        }

        if (monedaOrigen.equals("Euro")) {
            return obtenerTipoCambioEuro(monedaDestino);
        } else if (monedaDestino.equals("Euro")) {
            return 1.0 / obtenerTipoCambioEuro(monedaOrigen);
        } else {
            double tipoCambioEuro = obtenerTipoCambioEuro(monedaOrigen);
            double tipoCambioDestino = obtenerTipoCambioEuro(monedaDestino);

            return tipoCambioDestino / tipoCambioEuro;
        }
    }

    private static double obtenerTipoCambioEuro(String moneda) {
        switch (moneda) {
            case "Dólar":
                return TIPO_CAMBIO_DOLAR_EURO;
            case "Libra":
                return TIPO_CAMBIO_LIBRA_EURO;
            case "Yen":
                return TIPO_CAMBIO_YEN_EURO;
            case "Won":
                return TIPO_CAMBIO_WON_EURO;
            default:
                return 1.0;
        }
    }
}
