package org.semmellitis.chesar;

import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.VerboseMode;

public class JMHTest {

  @GenerateMicroBenchmark
  public void hikariPool() {}

  public static void main(String[] args) throws RunnerException {
    Options opt =
        new OptionsBuilder().include(".*" + JMHTest.class.getSimpleName() + ".*")
            .verbosity(VerboseMode.EXTRA).forks(1).build();

    new Runner(opt).run();
  }


}
