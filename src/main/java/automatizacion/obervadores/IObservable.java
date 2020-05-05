package automatizacion.obervadores;

public interface IObservable {
    public void notificar();
    public void agregarObserver(IObserver ... observers);
    public void eliminarObserver(IObserver observer);
}
