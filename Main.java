import java.util.ArrayList;
import java.util.List;

// Classe abstrata base para todas as formas geométricas
abstract class FormaGeometrica {
    public abstract double getArea();
    public abstract double getPerimetro();
}

// Classe Triângulo
class Triangulo extends FormaGeometrica {
    private double lado1;
    private double lado2;
    private double lado3;
    private double base;
    private double altura;

    // Construtor para triângulo equilátero
    public Triangulo(double lado) {
        this.lado1 = this.lado2 = this.lado3 = lado;
        this.altura = (lado * Math.sqrt(3)) / 2;
        this.base = lado;
    }

    // Construtor para triângulo isósceles
    public Triangulo(double ladosIguais, double base) {
        this.lado1 = this.lado2 = ladosIguais;
        this.lado3 = base;
        this.base = base;
        this.altura = Math.sqrt(Math.pow(ladosIguais, 2) - Math.pow(base/2, 2));
    }

    // Construtor para triângulo escaleno
    public Triangulo(double lado1, double lado2, double lado3) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        // Fórmula de Herão para altura
        double s = getPerimetro() / 2;
        this.altura = (2 / lado1) * Math.sqrt(s * (s - lado1) * (s - lado2) * (s - lado3));
        this.base = lado1;
    }

    @Override
    public double getArea() {
        return (base * altura) / 2;
    }

    @Override
    public double getPerimetro() {
        return lado1 + lado2 + lado3;
    }
}

// Classe Retângulo
class Retangulo extends FormaGeometrica {
    private double comprimento;
    private double largura;

    public Retangulo(double comprimento, double largura) {
        this.comprimento = comprimento;
        this.largura = largura;
    }

    // Construtor para quadrado (sobrecarga)
    public Retangulo(double lado) {
        this.comprimento = lado;
        this.largura = lado;
    }

    @Override
    public double getArea() {
        return comprimento * largura;
    }

    @Override
    public double getPerimetro() {
        return 2 * (comprimento + largura);
    }
}

// Classe Circunferência
class Circunferencia extends FormaGeometrica {
    private double raio;

    public Circunferencia(double raio) {
        this.raio = raio;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(raio, 2);
    }

    @Override
    public double getPerimetro() {
        return 2 * Math.PI * raio;
    }

    // Método específico para circunferência (diâmetro)
    public double getDiametro() {
        return 2 * raio;
    }
}

// Classe Pentágono
class Pentagono extends FormaGeometrica {
    private double lado;
    private double apotema;

    public Pentagono(double lado) {
        this.lado = lado;
        // Fórmula aproximada para o apótema de um pentágono regular
        this.apotema = lado / (2 * Math.tan(Math.PI / 5));
    }

    public Pentagono(double lado, double apotema) {
        this.lado = lado;
        this.apotema = apotema;
    }

    @Override
    public double getArea() {
        return (5 * lado * apotema) / 2;
    }

    @Override
    public double getPerimetro() {
        return 5 * lado;
    }
}

// Classe principal
public class Main {
    public static void main(String[] args) {
        // Criando uma lista de formas geométricas
        List<FormaGeometrica> formas = new ArrayList<>();
        
        formas.add(new Triangulo(5, 6, 7));
        formas.add(new Triangulo(4)); // equilátero
        formas.add(new Retangulo(5, 10));
        formas.add(new Retangulo(8)); // quadrado
        formas.add(new Circunferencia(3));
        formas.add(new Pentagono(5));
        
        // Mostrando áreas e perímetros de todas as formas
        System.out.println("Áreas e perímetros das formas:");
        for (FormaGeometrica forma : formas) {
            System.out.println(forma.getClass().getSimpleName() + ":");
            System.out.println("Área: " + forma.getArea());
            System.out.println("Perímetro: " + forma.getPerimetro());
            System.out.println();
        }
        
        // Filtrando apenas circunferências para mostrar raios e diâmetros
        List<Circunferencia> circunferencias = new ArrayList<>();
        for (FormaGeometrica forma : formas) {
            if (forma instanceof Circunferencia) {
                circunferencias.add((Circunferencia) forma);
            }
        }
        
        mostrarRaiosEDiametros(circunferencias);
    }
    
    // Função que recebe apenas circunferências (polimorfismo)
    public static void mostrarRaiosEDiametros(List<Circunferencia> circunferencias) {
        System.out.println("\nRaios e diâmetros das circunferências:");
        for (Circunferencia circ : circunferencias) {
            System.out.println("Raio: " + circ.getPerimetro() / (2 * Math.PI));
            System.out.println("Diâmetro: " + circ.getDiametro());
            System.out.println();
        }
    }
}