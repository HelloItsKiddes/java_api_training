package fr.lernejo;

import org.junit.jupiter.api.Assertions;

class SampleTest {

    @org.junit.jupiter.api.Test
    void op() {
        Sample toto = new Sample();
        Assertions.assertEquals(toto.op(Sample.Operation.ADD, 1, 5), 6);
        Assertions.assertEquals(toto.op(Sample.Operation.ADD, -5, 5), 0);
        Assertions.assertEquals(toto.op(Sample.Operation.ADD, 22, 0), 22);
        Assertions.assertEquals(toto.op(Sample.Operation.ADD, -5, -5), -10);
        Assertions.assertEquals(toto.op(Sample.Operation.ADD, 0, 0), 0);

        Assertions.assertEquals(toto.op(Sample.Operation.MULT, 1, 5), 5);
        Assertions.assertEquals(toto.op(Sample.Operation.MULT, -5, 5), -25);
        Assertions.assertEquals(toto.op(Sample.Operation.MULT, 22, 0), 0);
        Assertions.assertEquals(toto.op(Sample.Operation.MULT, -5, -5), 25);
        Assertions.assertEquals(toto.op(Sample.Operation.MULT, 0, 0), 0);
    }

    @org.junit.jupiter.api.Test
    void fact() {
        Sample toto = new Sample();

        Assertions.assertEquals(toto.fact(5), 120);
        Assertions.assertEquals(toto.fact(15), 2004310016);
        Assertions.assertEquals(toto.fact(1), 1);
        Assertions.assertEquals(toto.fact(0), 1);
    }
}
