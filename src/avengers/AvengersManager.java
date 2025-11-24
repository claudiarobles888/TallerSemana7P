package avengers;

import java.util.ArrayList;

public class AvengersManager {

    private ArrayList<Avengers> avengers;

    public AvengersManager() {
        avengers = new ArrayList<>();
    }

    public boolean registrarAvenger(Avengers avenger) {
        if (existeId(avenger.getId())) {
            return false;
        }
        avengers.add(avenger);
        return true;
    }

    public boolean modificarAvenger(String id, String nombre, String mision,
                                    int nivelPeligrosidad, double pagoMensual) {
        Avengers avenger = buscarAvenger(id);

        if (avenger == null) {
            return false;
        }

        avenger.setNombre(nombre);
        avenger.setMision(mision);
        avenger.setNivelPeligrosidad(nivelPeligrosidad);
        avenger.setPagoMensual(pagoMensual);

        return true;
    }

    public Avengers buscarAvenger(String id) {
        for (Avengers avenger : avengers) {
            if (avenger.getId().equals(id)) {
                return avenger;
            }
        }
        return null;
    }


    public boolean eliminarAvenger(String id) {
        Avengers avenger = buscarAvenger(id);

        if (avenger != null) {
            avengers.remove(avenger);
            return true;
        }

        return false;
    }

    public ArrayList<Avengers> listarAvengers() {
        return avengers;
    }

    public boolean existeId(String id) {
        for (Avengers avenger : avengers) {
            if (avenger.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    public int getCantidadAvengers() {
        return avengers.size();
    }


    public ArrayList<Avengers> buscarPorPeligrosidad(int nivel) {
        ArrayList<Avengers> resultado = new ArrayList<>();

        for (Avengers avenger : avengers) {
            if (avenger.getNivelPeligrosidad() == nivel) {
                resultado.add(avenger);
            }
        }

        return resultado;
    }

    public double calcularTotalPagosMensuales() {
        double total = 0;

        for (Avengers avenger : avengers) {
            total += avenger.getPagoMensual();
        }

        return total;
    }
}