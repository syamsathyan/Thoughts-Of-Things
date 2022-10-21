package com.ve.cxe.commons.ai.nn;

import org.junit.*;

import java.util.List;

public class NNTest {

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testXOR() {
        double[][] X = {
                {0, 0},
                {1, 0},
                {0, 1},
                {1, 1}
        };
        double[][] Y = {
                {0}, {1}, {1}, {0}
        };

        NeuralNetwork nn = new NeuralNetwork(2, 10, 1);


        List<Double> output;

        nn.fit(X, Y, 50000);
        double[][] input = {
                {0, 0}, {0, 1}, {1, 0}, {1, 1}
        };
        for (double d[] : input) {
            output = nn.predict(d);
            System.out.println(output.toString());
        }

    }
}
