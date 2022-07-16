package ru.otus.homework15.runner;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.otus.homework15.config.LarvaToButterflyGateway;
import ru.otus.homework15.model.Butterfly;
import ru.otus.homework15.model.Larva;

@Component
public class RunnerImpl implements ApplicationRunner {
    private final static int COUNT_TRANSFORM = 3;
    private final static long TIME_DURATION_BETWEEN_TRANSFORM = 2000L;
    private final static String[] NAMES_INSECT = {"Mary", "Helen", "Ann", "Ovius"};
    private final LarvaToButterflyGateway gateway;

    @Autowired
    public RunnerImpl(LarvaToButterflyGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < COUNT_TRANSFORM; i++) {
            new Thread(() -> {
                Larva larva = new Larva(generateInsectName());
                System.out.println("created Larva with name=" + larva.getName());
                Butterfly butterfly = gateway.process(larva);
                System.out.println("finish transform to butterfly with name=" +
                        butterfly.getName());
            }).start();
            Thread.sleep(TIME_DURATION_BETWEEN_TRANSFORM);
        }
    }

    private static String generateInsectName() {
        return NAMES_INSECT[RandomUtils.nextInt(0, NAMES_INSECT.length)];
    }
}
