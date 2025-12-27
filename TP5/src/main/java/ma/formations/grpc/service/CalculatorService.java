package ma.formations.grpc.service;

import io.grpc.stub.StreamObserver;
import ma.formations.grpc.stubs.Calculator;
import ma.formations.grpc.stubs.CalculatorServiceGrpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

    @Override
    public void sum(Calculator.UnaryRequest request,
                    StreamObserver<Calculator.UnaryResponse> responseObserver) {
        double a = request.getA();
        double b = request.getB();
        double result = a + b;
        Calculator.UnaryResponse response = Calculator.UnaryResponse.newBuilder().
                setA(a).
                setB(b).
                setResult(result).
                build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}