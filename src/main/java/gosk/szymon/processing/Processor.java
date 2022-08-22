package gosk.szymon.processing;

public interface Processor<T, R> {

    R process(T object);

}
