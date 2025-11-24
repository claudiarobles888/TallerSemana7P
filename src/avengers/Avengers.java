package avengers;

public class Avengers {
    private String id;
    private String nombre;
    private String mision;
    private int nivelPeligrosidad;
    private double pagoMensual;


    private static final double PORCENTAJE_FONDO = 0.08;

    public Avengers(String id, String nombre, String mision, int nivelPeligrosidad, double pagoMensual) {
        this.id = id;
        this.nombre = nombre;
        this.mision = mision;
        this.nivelPeligrosidad = nivelPeligrosidad;
        this.pagoMensual = pagoMensual;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public int getNivelPeligrosidad() {
        return nivelPeligrosidad;
    }

    public void setNivelPeligrosidad(int nivelPeligrosidad) {
        if (nivelPeligrosidad >= 1 && nivelPeligrosidad <= 5) {
            this.nivelPeligrosidad = nivelPeligrosidad;
        }
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        if (pagoMensual > 0) {
            this.pagoMensual = pagoMensual;
        }
    }

    public double calcularAporteFondo() {
        return pagoMensual * PORCENTAJE_FONDO;
    }


    public double calcularPagoAnual() {
        return pagoMensual * 12;
    }

    public double calcularImpuesto() {
        double pagoAnual = calcularPagoAnual();
        double impuesto = 0;

        if (pagoAnual <= 50000) {
            impuesto = 0;
        } else if (pagoAnual <= 100000) {
            impuesto = (pagoAnual - 50000) * 0.10;
        } else if (pagoAnual <= 200000) {
            impuesto = (50000 * 0.10) + ((pagoAnual - 100000) * 0.20);
        } else {
            impuesto = (50000 * 0.10) + (100000 * 0.20) + ((pagoAnual - 200000) * 0.30);
        }

        return impuesto;
    }

    public double calcularPagoNeto() {
        double pagoAnual = calcularPagoAnual();
        double aporteAnual = calcularAporteFondo() * 12;
        double impuesto = calcularImpuesto();

        return pagoAnual - aporteAnual - impuesto;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | %s | Misión: %s | Peligrosidad: %d | Pago: $%.2f",
                id, nombre, mision, nivelPeligrosidad, pagoMensual);
    }
}
