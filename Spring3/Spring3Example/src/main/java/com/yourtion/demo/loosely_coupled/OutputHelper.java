package com.yourtion.demo.loosely_coupled;

/**
 * Created by yourtion on 06/05/2017.
 */
public class OutputHelper {
    IOutputGenerator outputGenerator;

    public void generateOutput() {
        this.outputGenerator.generateOutput();
    }

    public void setOutputGenerator(IOutputGenerator outputGenerator) {
        this.outputGenerator = outputGenerator;
    }
}
