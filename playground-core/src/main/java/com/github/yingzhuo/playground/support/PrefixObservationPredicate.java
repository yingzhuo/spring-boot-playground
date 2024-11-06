package com.github.yingzhuo.playground.support;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationPredicate;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PrefixObservationPredicate implements ObservationPredicate {

    private final Set<String> disabledPrefix = new HashSet<>();

    public PrefixObservationPredicate(String first, String... mores) {
        this.disabledPrefix.add(first);
        this.disabledPrefix.addAll(Arrays.asList(mores));
    }

    @Override
    public boolean test(String name, Observation.Context context) {
        for (var it : this.disabledPrefix) {
            if (StringUtils.startsWithIgnoreCase(name, it)) {
                return false;
            }
        }
        return true;
    }

}
